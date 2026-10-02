# Last Checkpoint

A dystopian 2D arcade shooter developed with Java and LibGDX.

## About the Game

Last Checkpoint is a 2D arcade shooter set in the year 2084.

Humanity is close to extinction. Only 3% of the population has survived, while 97% of the world has been affected by a mysterious corruption.

The player takes the role of one of the last defenders of humanity's final checkpoint.

The objective is to stop corrupted creatures while protecting the remaining human survivors. The player has to distinguish between enemies and survivors while the difficulty increases throughout the game.

The game combines simple arcade gameplay with a dystopian atmosphere, visual effects, sound design and progressive difficulty.

## Gameplay

Before the game starts, an introductory sequence establishes the story and atmosphere of the game.

The player must:

- Shoot corrupted creatures
- Protect human survivors
- React to an increasing spawn rate
- Survive through three difficulty levels
- Reach higher scores while surviving as long as possible

The game becomes progressively more difficult by increasing the spawn rate and the speed of falling objects.

A perspective effect is also used to make the objects appear to have different depths, even though the game itself is fully 2D.

## Features

- Intro sequence and story introduction
- Last Checkpoint splash screen
- Three difficulty levels
- Increasing spawn rate
- Increasing object speed
- Perspective/depth effect for falling objects
- Shooting mechanics
- Collision detection
- Score system
- Pause functionality
- Game Over state
- Restart functionality
- Background music
- Intro music
- Shooting and impact sounds
- Keyboard controls

## Difficulty and Level System

The game contains three difficulty levels.

| Level | Score |
|---|---:|
| 1 | Start |
| 2 | 1,000 points |
| 3 | 4,000 points |

As the player progresses through the levels, the difficulty increases through:

- Higher spawn rates
- Faster falling objects
- Increased overall gameplay difficulty

The game also uses a perspective effect in which the size of falling objects changes depending on their position on the screen. This creates the visual impression of depth while keeping the game entirely 2D.

## Controls

| Key | Action |
|---|---|
| ← | Move left |
| → | Move right |
| SPACE | Shoot / skip intro |
| P | Pause |
| ENTER | Resume |
| TAB | Restart after Game Over |

## Project Structure

The project is organized into two main Gradle modules:

- `core` — contains the main game logic.
- `lwjgl3` — contains the desktop application configuration and launcher.

The main game logic is located in:

- `core/src/main/java/at/fhj/msd/catchgame/entities/`
  - `FallingObject.java`
  - `Player.java`
  - `Projectile.java`
- `core/src/main/java/at/fhj/msd/catchgame/world/`
  - `GameWorld.java`
- `core/src/main/java/at/fhj/msd/catchgame/`
  - `LASTCHECKPOINT.java`

### Main Game Logic

#### LASTCHECKPOINT.java

The main entry point of the game.

It is responsible for:

- Loading textures
- Loading sound effects
- Loading music
- Rendering game objects
- Processing user input
- Managing the intro
- Displaying the logo
- Displaying the game screen

#### GameWorld.java

Contains the main game logic and game state management.

It is responsible for:

- Managing the game state
- Spawning objects
- Managing the level system
- Collision detection
- Score calculation
- Detecting Game Over

#### Player.java

Represents the player character.

It is responsible for:

- Player position
- Player movement
- Player controls

#### Projectile.java

Represents the projectiles fired by the player.

It is responsible for:

- Projectile movement
- Collision detection with enemies

#### FallingObject.java

Represents the objects falling through the game area.

A falling object can represent either:

- `MONSTER`
- `HUMAN`

Both types are managed using the same underlying structure.

## Technology

The project was developed using:

- Java 11
- LibGDX
- Gradle
- Git

The project uses the Gradle Wrapper, so Gradle does not need to be installed globally to run the application.

## How to Run

### Requirements

A Java environment compatible with Java 11 is required.

### Run the Game

From the project root, run:

`./gradlew run`

The command starts the game as a JVM desktop application.

The Gradle Wrapper included in the repository is used to manage the build and execution process.

## Project Background

Last Checkpoint was originally developed collaboratively as part of the FH Joanneum Mobile Software Development Bootcamp.

The project started from a simple Catch-Game prototype and was developed into a standalone dystopian arcade game with its own story, visual identity, sound design and gameplay mechanics.

The original project was developed collaboratively by a team of students.

This repository is a personal copy of the project published as part of a personal software development portfolio.

## My Contribution

My main contribution to the project was the original game concept and the general direction of the idea.

The implementation was collaborative and developed together with other members of the team.

The project is therefore presented as a collaborative development project rather than as a project developed entirely by one person.

## Assets

The game uses a combination of visual and audio assets, including:

- Character graphics
- Enemy graphics
- Player graphics
- Projectile graphics
- Background graphics
- Game logo
- Background music
- Intro music
- Shooting sounds
- Impact sounds
- Game Over sound
- LibGDX-related graphics

The complete list of assets used by the project can be found in `assets/assets.txt`.

Information about asset sources and licensing will be documented separately in `ASSET-LICENSES.md`.

## Project Status

This repository represents the completed version of the game developed during the Bootcamp and published as part of a personal portfolio.

The current goal of this repository is to preserve and present the project as a completed development project rather than to continue active feature development.

## Author

**Andres Fierro**

Personal GitHub: https://github.com/andresfierrodev

## Acknowledgements

Last Checkpoint was created as a collaborative student project during the FH Joanneum Mobile Software Development Bootcamp.

The project was developed together with the other members of the original team, whose contributions were an important part of the final result.