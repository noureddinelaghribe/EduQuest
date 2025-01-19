# Etudtion - A Student's Study App

EduQuest is an Android application designed to help students manage their study time, track learning progress, and utilize different tools for effective study sessions. The app features a floating window that appears when the screen is turned on and allows users to interact with it easily.

## Screenshots

Here are some screenshots of theEduQuest App for Android :

| Screenshot 1 | Screenshot 2 |
|--------------|--------------|
| ![Screenshot 1](https://raw.githubusercontent.com/noureddinne21/EduQuest/refs/heads/master/photo_2025-01-19_17-13-23.jpg) | ![Screenshot 2](https://raw.githubusercontent.com/noureddinne21/EduQuest/refs/heads/master/photo_2025-01-19_17-13-19.jpg) |
| ![Screenshot 3](https://raw.githubusercontent.com/noureddinne21/EduQuest/refs/heads/master/photo_2025-01-19_17-13-15.jpg) | ![Screenshot 4](https://raw.githubusercontent.com/noureddinne21/EduQuest/refs/heads/master/photo_2025-01-19_17-13-03.jpg) |

## Features
- **Floating Window:** Displays a floating window when the screen is turned on.
- **Room Database:** Utilizes Room for storing and managing data locally, keeping track of study sessions, tasks, and notes.
- **MVVM Architecture:** Implemented using the Model-View-ViewModel (MVVM) pattern to separate concerns and maintain clean code.
- **BroadcastReceiver:** Uses a BroadcastReceiver to monitor system events like screen on/off to trigger floating window visibility.
- **Chat GPT Integration:** A built-in chatbot powered by GPT for answering study-related questions or providing explanations on various topics.

## Technologies Used
- **Java**: Primary programming languages used for Android development.
- **Room Database**: For storing local data such as study sessions and tasks.
- **MVVM Architecture**: For managing UI-related data lifecycle-consciously.
- **BroadcastReceiver**: For detecting screen state changes.
- **Floating Window**: For displaying an always-on-top widget.

## What I Want to Learn
- How to implement a floating window that automatically appears when the screen is turned on.
- Managing data efficiently with Room Database in MVVM architecture.
- Optimizing system resource usage while displaying a floating window continuously.
- Integrating AI-based chat functionalities like GPT for helping students with their studies.
- Exploring the use of BroadcastReceivers for system event handling.

## Getting Started

### Prerequisites
- Android Studio with an updated SDK.
- Knowledge of Kotlin or Java for Android development.
- Room Database dependencies in your build.gradle file.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Etudtion.git
