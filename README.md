# Gemini-AI SDK Sample Usage

This project demonstrates the usage of the Gemini-AI SDK for Android. It showcases how to integrate the Gemini-AI SDK into your Android application and utilize its features.

SDK : https://ai.google.dev/tutorials/android_quickstart

## Introduction

Briefly introduce Gemini-AI SDK and its capabilities. Highlight any key features or benefits it offers to developers.

## Installation

To integrate Gemini-AI SDK into your Android project, follow these steps:

Before you begin, ensure you have an API key, You can generate API from here: https://aistudio.google.com/

Create ```secrets.properties``` file at the project level and add the API key

```
apiKey=YOUR API KEY
```
### This simple demo uses three features from Generative AI SDK.

- Generate text from text-only input
- Generate text from text-and-image input (multimodal)
- Build multi-turn conversations (chat)

### Sample

![https://github.com/maulik22995/Gemini-Sample/blob/master/sample/gemini.png](https://github.com/maulik22995/Gemini-Sample/blob/master/sample/gemini.png)

Check out the full sample video: https://drive.google.com/file/d/1t8wSbivJiuJoz3lWtUjoer3ASZbDQkAM/view?usp=sharing

## Used Language, Architecture & Framework

```
 ● Jetpak compose UI toolkit
 ● Kotlin
 ● MVVM design pattern and Jetpack
 ● Hilt dependency injection framework
 ● Coroutines
```

## 🛠️ Tech Stack

| Library          | About                                         |   Version    |
| ---------------- | ----------------------------------------------| ------------ |
| compose Bom      | UI- toolkit                                   | 2024.02.00   |
| Hilt             | Dependency injection framework                | 2.48         |
| coroutine        | Kotlin corutine framwork                      | 1.3.9        |
| coil             | compose image loading                         | 2.5.0        |
| genrativeAI      | Google Generative AI sdk                      | 0.2.0        |


## 🚀 To run on an Android device

  ```
   Open Android Studio,
  
   Clone the repo,
   https://github.com/maulik22995/Gemini-Sample.git

   Build the project,

   Connect the Android device and enable USB debugging,
    
   Hit "Run". Done!
  ``` 
