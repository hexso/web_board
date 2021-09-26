import React, {Component} from "react";
import { Container, Row, Col } from "shards-react";

import PageTitle from "../components/common/PageTitle";
import Editor from "../components/add-new-post/Editor";
import SidebarActions from "../components/add-new-post/SidebarActions";
import SidebarCategories from "../components/add-new-post/SidebarCategories";
import BoardService from "../service/BoardService";

class AddNewPost extends Component { 
  constructor(props){
    super(props);
        this.state = {
            no: 1,
            postType: 'Photo',
            title: '',
            contents: '',
            authorId: 1,
            file : ''
        }

        this.changeTitleHandler = this.changeTitleHandler.bind(this);
        this.changeContentsHandler = this.changeContentsHandler.bind(this);
        this.changeTypeHandler = this.changeTypeHandler.bind(this);
        this.createPostHandler = this.createPostHandler.bind(this);
    }

    createPostHandler = (event) => {
      const FormData = require('form-data');
      const form = new FormData();
      let board = {
          postType: this.state.postType,
          title: this.state.title,
          contents: this.state.contents,
          authorId: this.state.authorId
      };
      
      form.append('file',this.state.file);
      form.append('body', new Blob([JSON.stringify(board)],{ type: "application/json" }));
      BoardService.createBoard(form).then(res => {
        this.props.history.push('/blog-posts');
      });
    }

    changeTypeHandler = (event) => {
      this.setState({postType: event.target.value});
    }

    changeTitleHandler = (event) => {
      this.setState({title: event.target.value});
    }

    changeContentsHandler = (value) => {
      this.setState({contents: value});
    }

    changeFileHandler = event => {
      const files = event.target.files[0];
      console.log(files);
      this.setState({
        file: files
      });
    };

    render() {return (
      <Container fluid className="main-content-container px-4 pb-4">
        {/* Page Header */}
        <Row noGutters className="page-header py-4">
          <PageTitle sm="4" title="Add New Post" subtitle="Blog Posts" className="text-sm-left" />
        </Row>

        <Row>
          {/* Editor */}
          <Col lg="9" md="12">
            <Editor handler={[this.changeTitleHandler, this.changeContentsHandler, this.changeFileHandler]}/>
          </Col>

          {/* Sidebar Widgets */}
          <Col lg="3" md="12">
            <SidebarActions handler={this.createPostHandler}/>
            <SidebarCategories handler={this.changeTypeHandler}/>
          </Col>
        </Row>
      </Container>
      );}
}

export default AddNewPost;
