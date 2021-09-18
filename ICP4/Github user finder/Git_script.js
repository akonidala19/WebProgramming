function getGithubInfo(user) {
    //Create an instance of XMLHttpRequest class and send a GET request using it.
    // The function should finally return the object(it now contains the response!)

    var username = user;
    var requestedurl = 'https://api.github.com/users/' + username;
    var repositoryurl = 'https://api.github.com/users/' + username + '/repos';
    $.ajax({
        type: "GET",
        url: requestedurl,
        dataType: "html",
        success: function (data) {
            console.log(data);
            displayUser(JSON.parse(data), repositoryurl);
        },
        error: function () {
            noUser(username);
        }
    });
}

function displayUser(user, repositoryurl) {
    // debugger;
    var fullname = user.name;
    var id = user.id;
    var username = user.login;
    var aviurl = user.avatar_url;
    var profileurl = user.html_url;
    var followersnum = user.followers;
    var followingnum = user.following;
    var reposnum = user.public_repos;
    var createddate = user.created_at;
    var updateddate = user.updated_at;
    var bio = user.bio;
    var loc = user.location;


    if (fullname == undefined) { fullname = username; }

    var outhtml = '<h2>Name : ' + fullname + ' <h2 class="smallname"><br>Github Link: (@<a href="' + profileurl + '" target="_blank"> ' + username + '</a>)</h2></h2>';
    outhtml = outhtml + '<p class="idname"><h2> ID: ' + id +'</h2></p>';
    outhtml = outhtml + '<div class="ghcontent"><div class="avi"><a href="' + profileurl + '" target="_blank"><img src="' + aviurl + '" width="150" height="150" alt="' + username + '"></a></div>';
  /*  outhtml = outhtml + '<p>Followers: ' + followersnum + '  Following: ' + followingnum + '<br>Repos: ' + reposnum + '</p></div>';*/
    outhtml = outhtml + '<p>Followers: ' + followersnum + '  <br> Following: ' + followingnum + '<br>Repos: ' + reposnum + '  <br>Location: ' + loc + '   <br>Bio: ' + bio + ' <br>Created At: ' + createddate + ' <br>Updated At: ' + updateddate + '</p></div>';
    outhtml = outhtml + '<div class="repolist clearfix">';

    var repositories;
    $.getJSON(repositoryurl, function (json) {
        repositories = json;
        outputPageContent();
    });
    function outputPageContent() {
        if (repositories.length == 0) { outhtml = outhtml + '<p>No repos!</p></div>'; }
        else {
            outhtml = outhtml + '<p><strong>Repos List:</strong></p> <ul>';
            $.each(repositories, function (index) {
                outhtml = outhtml + '<li><a href="' + repositories[index].html_url + '" target="_blank">' + repositories[index].name + '</a></li>';
            });
            outhtml = outhtml + '</ul></div>';
        }

        $('#profile').html(outhtml);
    }
    //et the contents of the h2 and the two div elements in the div '#profile' with the user content
}

function noUser(username) {

    alert('No Username Found');
    //set the elements such that a suitable message is displayed
}

$(document).ready(function () {
    $(document).on('keypress', '#username', function (e) {
        //check if the enter(i.e return) key is pressed
        if (e.which == 13) {
            //get what the user enters
            username = $(this).val();
            //reset the text typed in the input
            $(this).val("");
            //get the user's information and store the respsonse
            getGithubInfo(username);
        }
    })
});