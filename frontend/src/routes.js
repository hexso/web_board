import React from "react";
import { Redirect } from "react-router-dom";

// Layout Types
import { DefaultLayout } from "./layouts";

// Route Views
import AddNewPost from "./views/AddNewPost";
import BlogPosts from "./views/BlogPosts";
import ReadPosts from "./views/ReadPosts";
import Login from "./views/Login";

export default [
  {
    path: "/",
    exact: true,
    layout: DefaultLayout,
    component: () => <Redirect to="/login" />
  },
  {
    path: "/add-new-post",
    layout: DefaultLayout,
    component: AddNewPost
  },
  {
    path: "/blog-posts",
    layout: DefaultLayout,
    component: BlogPosts
  },
  {
    path: "/read-post/:no",
    layout: DefaultLayout,
    component: ReadPosts
  },
  {
    path: "/login",
    layout: DefaultLayout,
    component: Login
  }
];
