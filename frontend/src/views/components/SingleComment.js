import React from 'react';

export default function SingleComment({comment}){
    return (
        <div className='comment'>
            <div className='writter'>작성자:{comment.writter}</div>
            <div className='date'>{comment.date}</div>
            <div className='content'>{comment.content}</div>
            <div><br/></div>
        </div>
    )
}