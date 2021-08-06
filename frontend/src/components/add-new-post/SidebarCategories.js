import React from "react";
import PropTypes from "prop-types";
import {
  Card,
  CardHeader,
  CardBody,
  ListGroup,
  ListGroupItem,
  FormSelect
} from "shards-react";

const SidebarCategories = (props) => {

  return(
  <Card small className="mb-3">
    <CardHeader className="border-bottom">
      <h6 className="m-0">카테고리</h6>
    </CardHeader>
    <CardBody className="p-0">
      <ListGroup flush>
        <ListGroupItem className="px-3 pb-2">
          <FormSelect onClick={props.handler}>
            <option value="Photo">Photo</option>
            <option value="Video">Video</option>
          </FormSelect>
        </ListGroupItem>
      </ListGroup>
    </CardBody>
  </Card>
);}

SidebarCategories.propTypes = {
  /**
   * The component's title.
   */
  title: PropTypes.string
};

SidebarCategories.defaultProps = {
  title: "Categories"
};

export default SidebarCategories;
