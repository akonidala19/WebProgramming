$(function() {
    $("button").click(function () {
        let userChoice = $(this).attr('id');
        startgame(userChoice);
    });
})

function startgame(userChoice) {
    let oppChoice = Math.random();
    let opponentChoice='';
    if (oppChoice<0.34)
        opponentChoice='rock';
    else if (oppChoice<0.67)
        opponentChoice='paper';
    else
        opponentChoice='scissors';

    if (userChoice === opponentChoice) {
        alert("Its a tie! Try again");
        console.log("User choice and opponentChoice are similar");
    }
    else if (userChoice == "rock"){
        if (opponentChoice == "scissors"){
            console.log("User choice is Rock and opponentChoice is Scissors");
            alert( "Congrats You Won !!! Your opponent had scissors");
        }

        else{
            console.log("User choice is Rock and opponentChoice is Paper");
            alert("Sorry You Lost !!! Your opponent had Papers");
        }
        }

    else if (userChoice == "paper") {
        if (opponentChoice == "rock") {
            console.log("User choice is Paper and opponentChoice is Rock");
            alert("Congrats You Won !!! Your opponent had rock");
        } else {
            console.log("User choice is Paper and opponentChoice is Scissors");
            alert("Sorry You Lost !!! Your opponent had scissors");
        }
    }
    else if (userChoice == "scissors") {
        if (opponentChoice == "paper") {
            console.log("User choice is Scissors and opponentChoice is Papers");
            alert("Congrats You Won !!! Your opponent had paper");
        } else {
            console.log("User choice is Scissors and opponentChoice is Rock");
            alert("Sorry You Lost !!! Your opponent had rock");
        }
    }
}