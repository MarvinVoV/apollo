package com.amorn.convert.controller;

import com.amorn.convert.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sunyameng on 2014/5/12.
 */
@Controller
@RequestMapping("/convert")
public class ConvertController {
    private static Logger logger= LoggerFactory.getLogger(ConvertController.class);
    @InitBinder
    public void initBinder(WebDataBinder binder){
    }
    @RequestMapping(value="formPage.do",method = RequestMethod.GET)
    public String formPage(Model model) {
        model.addAttribute("article", new Article());
        return "convert/form";
    }
    @RequestMapping(value="formAction.do",method = RequestMethod.POST)
    public String formAction(@ModelAttribute Article article,BindingResult result,Model model) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                logger.info("Validation error: "+ error.getDefaultMessage());
            }
            return "convert/form";
        }
        return "convert/formResult";
    }
}
