# Project: XO++

**Description:** XO++ is a more exciting and thrilling of the orignal XO(Tic Tac Toe). XO++ has 3 play modes such as,
- Classic: The original Tic Tac Toe with winning options such as 3 to win and 5 to win
- Best of 5: The first player to score 3 lines wins. Play continously without resetting the board after each score
- Portal: A 4x4 board with portal on the vertical and horizontal sides.

To make it more fun and less boring to play, the *Rapid* option can be enabled. With this option enabled each player has 5 seconds to play their turns. If a player does not make a move during that 5 seconds, that player loses.

**Tools:** JavaFX for graphic and interaction with mouse

## How to install and run

1. First download the `javafx-sdk-21.0.6` package from https://gluonhq.com/products/javafx/
2. Import the library into the project.
3. Create project runner:
- For Intellij: set main class to "application.Main", set vmOptions to "--module-path **path-to-package**/javafx-sdk-21.0.6/lib --add-modules javafx.controls,javafx.fxml"
- For vscode: overwrite launch.json with this
<pre>
 {
    // Use IntelliSense to learn about possible attributes.
    // Hover to view descriptions of existing attributes.
    // For more information, visit: https://go.microsoft.com/fwlink/?linkid=830387
    "version": "0.2.0",
    "configurations": [ {
           "type": "java",
            "name": "Main",
            "request": "launch",
            "mainClass": "application.Main",
            "vmArgs": "--module-path /Users/mac/javafx-sdk-21.0.6/lib --add-modules javafx.controls,javafx.fxml"
        },
    ]
}
</pre>
4. Finally you can run the Main.java
 
