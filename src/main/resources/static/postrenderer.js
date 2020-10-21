<!-- Post Renderer -->

var getJSON = function(url) {
    return new Promise(function(resolve, reject) {
    var xhr = new XMLHttpRequest();
    xhr.open('get', url, true);
    xhr.responseType = 'json';
    xhr.onload = function() {
        var status = xhr.status;
        if (status == 200) {
        resolve(xhr.response);
    } else {
    reject(status);
    }
    };
    xhr.send();
    });
};

function displayComments(comments){
    var l = comments.length;
    for(j=0;j<l;j++){
        $('#posts_table table').append( '<tr><td>Comments&nbsp;</td><td>' + comments[j].body + '</td></tr>');
        $('#posts_table table').append( '<tr><td>By&nbsp;</td><td>' + comments[j].email + '</td></tr>');
        $('#posts_table table').append( '<tr><td>&nbsp;</td><td>&nbsp;</td></tr>');
    }
 }

function displayPost(post){
    $('#posts_table table').append( '<tr><td nowrap>Post&nbsp;ID</td><td>' + post.id + '</td></tr>');
    $('#posts_table table').append( '<tr><td nowrap>Posted by User&nbsp;</td><td>' + post.userId + '</td></tr>');
    $('#posts_table table').append( '<tr><td>Title</td><td>' + post.title + '</td></tr>');
    $('#posts_table table').append( '<tr><td>Post</td><td>' + post.body + '</td></tr>');
    $('#posts_table table').append( '<tr><td><hr></td><td><hr></td></tr>');
}


function getPosts() {
    var my = $.getJSON('http://localhost:8080/posts').done(function(data) {
            displayPosts(data);
            }, function(status) {

            //alert("An error occurred, please contact the Helpdesk.");
        }
    )
}//getPosts

function displayPosts(jsonArray){
    var l = jsonArray.length;
    for(i=0;i<l;i++){
        displayPost(jsonArray[i]);
        var my = $.getJSON('http://localhost:8080/comments?postId='+jsonArray[i].id).done(function(data) {
        displayComments(data);
        })
    }
}//displayPosts

getPosts();







