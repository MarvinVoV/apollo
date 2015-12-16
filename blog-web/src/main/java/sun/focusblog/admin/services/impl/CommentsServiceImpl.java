package sun.focusblog.admin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.focusblog.admin.dao.ICommentsDao;
import sun.focusblog.admin.domain.Comment;
import sun.focusblog.admin.services.CommentsService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        boolean flag = false;    // fix data loss when page changed
        for (Comment comment : list) {
            if (!flag) {
                flag = true;
                if (comment.getParentId() != 0) {
                    Comment root = backtraceForRootNode(comment);
                    if (root != null) {
                        treeList.add(root);
                    }
                }
            }
            if (comment.getParentId() == 0) { // single root node
                treeList.add(comment);
            } else {
                appendNode(treeList, comment);
            }
        }
        return treeList;
    }

    /**
     * Trace back to find this root comment
     *
     * @param comment comment
     * @return root comment which parentId is zero
     */
    private Comment backtraceForRootNode(Comment comment) {
        if (comment == null || comment.getParentId() == 0) {
            return comment;
        }
        Comment node = this.query(comment.getId());
        comment.setParent(node);
        return backtraceForRootNode(node);
    }


    private void appendNode(List<Comment> list, Comment comment) {
        Comment parent = null;
        for (Comment node : list) {
            parent = traverse(node, comment);
            if (parent != null) {
                break;
            }
        }
        if (parent != null) {
            parent.getChildren().add(comment);
            comment.setParent(parent);
        }
    }

    private Comment traverse(Comment parent, Comment node) {
        Comment retVal = null;
        if (parent.getId() == node.getParentId()) {
            retVal = parent;
        } else {
            List<Comment> list = parent.getChildren();
            for (Comment comment : list) {
                retVal = traverse(comment, node);
            }
        }
        return retVal;
    }

    @Override
    public Comment query(int id) {
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
}
