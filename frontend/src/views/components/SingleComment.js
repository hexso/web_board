import React from 'react';

export default function SingleComment({comment}){
    return (
        <div className='comment'>
            <div className='nickName'>작성자:{comment.user.nickName}</div>
            <div className='date'>{comment.date}</div>
            <div className='content'>{comment.contents}</div>
            <div><br/></div>
        </div>
    )
}