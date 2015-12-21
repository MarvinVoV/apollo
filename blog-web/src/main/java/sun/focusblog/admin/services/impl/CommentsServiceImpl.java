package sun.focusblog.admin.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.focusblog.admin.dao.ICommentsDao;
import sun.focusblog.admin.domain.Comment;
import sun.focusblog.admin.domain.auth.User;
import sun.focusblog.admin.services.CommentsService;

import java.util.*;

/**
 * Created by root on 2015/12/16.
 * <p/>
 * Article comments service
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private ICommentsDao commentsDao;

    @Value("${personal.blog.comment.index:1}")
    private String pageIndex;

    @Value("${personal.blog.comment.pageSize:10}")
    private String pageSize;

    @Override
    public List<Comment> defaultList(String articleId) {
        return list(articleId, Integer.valueOf(pageIndex), Integer.valueOf(pageSize));
    }

    @Override
    public List<Comment> list(String articleId, int start, int size) {
        List<Comment> list = commentsDao.list(articleId, start, size);
        if (list == null || list.size() == 0) {
            return new ArrayList<>();
        }

        List<Comment> treeList = new LinkedList<>();

        int i = 0;
        for (Comment comment : list) {
            if (comment.getParent() == null) {
                boolean flag = false;
                for (Comment com : treeList) {
                    if (com.getId().equals(comment.getId())) {
                        flag = true;
                    }
                }
                if (!flag) {
                    treeList.add(comment);
                }
            } else {
                if (i == 0 && treeList.size() == 0) {  // Bounds checking
                    i = -1;
                    Comment rootNode = traceBackForRoot(comment);
                    if (rootNode != null) {
                        treeList.add(rootNode);
                    }
                } else {
                    appendNode(treeList, comment);
                }
            }
        }

        return treeList;
    }

    private void appendNode(List<Comment> treeList, Comment comment) {
        for (Comment node : treeList) {
            if (traverseNode(node, comment)) {
                break;
            }
        }
    }

    /**
     * build relation between parent and chilren
     *
     * @param node    node of tree
     * @param comment line head node
     * @return true find
     */
    private boolean traverseNode(Comment node, Comment comment) {
        // Fix child parent relationship
        List<Comment> nodeChildren = node.getChildren();
        if (nodeChildren != null && nodeChildren.size() > 0) {
            for (Comment nodeChild : nodeChildren) {
                nodeChild.setParent(node);
            }
        }
        // Build tree structure
        if (node.getId().equals(comment.getId())) {
            List<Comment> children = comment.getChildren();
            node.setChildren(children);
            if (children != null && children.size() > 0) {
                for (Comment child : children) {
                    child.setParent(node);
                }
            }
            return true;
        } else {
            for (Comment child : node.getChildren()) {
                if (traverseNode(child, comment)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Trace back to find the root node
     *
     * @param comment comment
     * @return root comment
     */
    private Comment traceBackForRoot(Comment comment) {
        Comment node = commentsDao.query(comment.getParent().getId());
        if (node == null) {
            return null;
        }
        // Fix child parent relationship
        List<Comment> children = node.getChildren();
        if (children != null && children.size() > 0) {
            for (Comment nodeChild : children) {
                nodeChild.setParent(node);
            }
        }

        if (node.getParent() != null) {
            return traceBackForRoot(node);
        }
        return node;
    }

    @Override
    public Comment query(String id) {
        return commentsDao.query(id);
    }

    @Override
    public int count(String articleId) {
        return commentsDao.count(articleId);
    }

    @Override
    public int getPageSize() {
        return Integer.valueOf(this.pageSize);
    }

    @Override
    public boolean save(Comment comment, User user) {
        if (comment != null && user != null) {
            if (StringUtils.isEmpty(comment.getId())) {
                comment.setId(UUID.randomUUID().toString());
            }
            comment.setUser(user);
            comment.setCommentDate(new Date());
            return commentsDao.save(comment);
        }
        return false;
    }
}
