import React from "react";
import ReactQuill from "react-quill";
import { Card, CardBody, Form, FormInput } from "shards-react";

import "react-quill/dist/quill.snow.css";
import "../../assets/quill.css";

const Editor = (props) => {

  return(
  <Card small className="mb-3">
    <CardBody>
      <Form className="add-new-post">
        <input type="file" multiple onChange={props.handler[2]} />
        <FormInput size="lg" className="mb-3" placeholder="Your Post Title" onChange={props.handler[0]}/>
        <ReactQuill className="add-new-post__editor mb-1" onChange={(content, delta, source, editor) => props.handler[1](editor.getText())}/>
      </Form>
    </CardBody>
  </Card>
  );}
export default Editor;
