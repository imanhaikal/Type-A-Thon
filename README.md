# The Type-A-Thon

## 1. Introduction
In the midst of the violet and pastel faculty building of University Kalaya, the computer science students face a challenge of losing their individuality. To combat this, the faculty has introduced a series of challenges, and your group is tasked with creating the Type-A-Thon. This challenge involves typing out randomly generated text within a given time frame to determine the fastest typist among the students.

## 2. Problem Statement

### 2.1. Main Game
#### Text
The generated text consists of random English words in lowercase. The text pool should be sufficiently large, making it impossible for users to run out of text before the timer expires.

#### Timer
A 30-second timer starts counting down as soon as the user types the first letter of the first word.

#### Score
The final score is calculated in Words Per Minute (WPM), considering the total characters in correctly typed words. The program tracks mistakes, and an accuracy percentage is displayed.

#### Gameplay
Players type the prompted text using their keyboards. They can correct errors by backspacing or move on to the next word by pressing the space bar. After the level, players can choose to repeat the same prompt or start again with a new one.

### 2.2. Alternate Gamemodes
#### Timed
- Players can choose whether punctuation is included in the randomly generated text.
- The timer can be adjusted to 15, 30, 45, or 60 seconds.

#### Words
- Players choose between 10, 25, 50, or 100 randomly generated words.
- A stopwatch measures how long it takes to complete the text.

#### Quotes
- Players type out a randomly selected quote from various media.
- Time and score are calculated similarly to the Words gamemode.
- Include the source of the text at the end of the round.

### 2.3. Profiles
Players can create accounts to store their scores, including:
#### Player Profiles
- Average WPM and accuracy scores (all-time).
- Average WPM and accuracy scores (last 10 games).

#### Leaderboards
- A leaderboard based on the average WPM (last 10 games) is updated with recent player scores.

## 3. Sample Input & Output
- Provide examples of the classic mode and Quotes gamemode.

## 4. Extra Features
Choose from the following extra features:

### 4.1. Correction Facility
- Generate a random text using the player’s most commonly mistyped words.
- Include a list of the user’s 10 most misspelled words in their profile.

### 4.2. Sudden Death
- A gamemode where a single mistake ends the game.
- Include a "sudden death score" category on the user’s profile.

### 4.3. Performance Metrics
- Record WPM at each second of the game and create graphs showing the progression.
- Create separate graphs for accuracy score.

### 4.4. GUI
- Implement a graphical user interface for a more user-friendly experience.

## 5. Questions
For any questions or concerns, contact Ahmed Ibrahim at aeiouhmed@gmail.com or +60147692109.

## References
If needed, refer to MonkeyType or TypeRacer for inspiration.

Feel free to modify and expand on this template based on your specific implementation and preferences.
