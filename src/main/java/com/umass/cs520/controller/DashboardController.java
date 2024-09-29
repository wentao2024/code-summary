package com.umass.cs520.controller;

import com.umass.cs520.service.AuthService;
import com.umass.cs520.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class DashboardController {
  @Autowired
  private AuthService authService;

  @RequestMapping(value = "/signup", method = RequestMethod.GET)
  public ModelAndView signup(@ModelAttribute("model") ModelMap model) {
    return new ModelAndView("index", model);
  }
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView login(@ModelAttribute("model") ModelMap model) {
    if (RequestUtil.getUser() != null) {
      return new ModelAndView(new RedirectView("/code-summarization"));
    }

    return new ModelAndView("login", model);
  }
  @RequestMapping(value = "/code-summarization", method = RequestMethod.GET)
  public ModelAndView codeSummarization(@ModelAttribute("model") ModelMap model) {
    model.put("user", RequestUtil.getUser());

    return new ModelAndView("code-summarization", model);
  }
  @RequestMapping(value = "/result-evaluation", method = RequestMethod.GET)
  public ModelAndView resultEvaluation(@ModelAttribute("model") ModelMap model) {
    model.put("user", RequestUtil.getUser());

    return new ModelAndView("result-evaluation", model);
  }
  @RequestMapping(value = "/manage", method = RequestMethod.GET)
  public ModelAndView userManage(@ModelAttribute("model") ModelMap model) {
    model.put("user", RequestUtil.getUser());

    model.put("users", authService.listUsers());

    return new ModelAndView("manage", model);
  }
  @RequestMapping(value = "/admin-change", method = RequestMethod.GET)
  public ModelAndView adminChange(@ModelAttribute("model") ModelMap model) {
    model.put("user", RequestUtil.getUser());

    return new ModelAndView("admin-change", model);
  }
}
