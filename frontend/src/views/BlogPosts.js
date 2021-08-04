/* eslint jsx-a11y/anchor-is-valid: 0 */

import React from "react";
import {
  Container,
  Row,
  Col,
  Card,
  CardBody,
  CardFooter
} from "shards-react";
import BoardService from '../service/BoardService';
import PageTitle from "../components/common/PageTitle";

class BlogPosts extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      p_num:1,
      paging:{},
      posts:[],
      image_url:'null'
    };
  }
  componentDidMount(){
    BoardService.getBoards(this.state.p_num).then((res) => {
      this.setState({ 
          p_num: res.data.pagingData.currentPageNum,
          paging: res.data.pagingData,
          posts: res.data.list});
    });
  }

  listBoard(p_num){
    console.log("pageNum : "+ p_num);
    BoardService.getBoards(p_num).then((res) => {
        console.log(res.data);
        this.setState({ 
            p_num: res.data.pagingData.currentPageNum,
            paging: res.data.pagingData,
            posts: res.data.list});
    });
  }

  getPreImage(multimediaList){
    if (multimediaList.length === 0){
      return null;
    }else{
      return multimediaList[0].url
    }
  }

  render() {
    return (
      <Container fluid className="main-content-container px-4">
        {/* Page Header */}
        <Row noGutters className="page-header py-4">
          <PageTitle sm="4" title="Blog Posts" subtitle="Components" className="text-sm-left" />
        </Row>

        {/* First Row of Posts */}
        <Row>
          {this.state.posts.map((post, idx) => (
            <Col lg="3" md="6" sm="12" className="mb-4" key={idx}>
              <Card small className="card-post h-100">
                <div
                  className="card-post__image"
                  style={{ backgroundImage: `url(${this.getPreImage(post.multimediaSet)})` }}
                >
                </div>
                <CardBody>
                  <h5 className="card-title">
                    <a href="#" className="text-fiord-blue">
                      {post.title}
                    </a>
                  </h5>
                  <span className="text-muted">{post.updatedTime}</span>
                </CardBody>
                <CardFooter className="text-muted border-top py-3">
                  <span className="d-inline-block">
                    By{"  "}
                    <a className="text-fiord-blue" href={post.authorUrl}>
                      {post.user.nickName}
                    </a>{" "}
                    in
                    <a className="text-fiord-blue" href={post.categoryUrl}>
                      {post.postType}
                    </a>
                  </span>
                </CardFooter>
              </Card>
            </Col>
          ))}
        </Row>
      </Container>
    );
  }
}

export default BlogPosts;
