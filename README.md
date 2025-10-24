# Daily Notes App

A simple Android app to create, view, and delete notes.

---

## 📱 Overview
Daily Notes helps users quickly write and manage short notes or reminders.  
It’s built with **Kotlin**, uses **XML** for UI, and **Firebase** for data storage.

---

## ✨ Features
- Add new notes  
- View all saved notes  
- Delete notes  
- Simple and clean user interface  

---

## 🛠️ Tech Stack
- **Language:** Kotlin  
- **UI Design:** XML  
- **Backend:** Firebase (Realtime Database or Firestore)  

---

## ⚙️ How to Run
1. Open the project in **Android Studio**  
2. Add your `google-services.json` file inside the `app/` folder (for Firebase)  
3. Connect your device or emulator  
4. Click **Run ▶️** to build and start the app  

---

## 📂 Folder Structure
app/  
├─ java/com/example/dailynotes → Kotlin source files  
├─ res/layout → XML UI layouts  
├─ google-services.json → Firebase config file  
└─ AndroidManifest.xml → App manifest

---

## 💾 Note Model
```kotlin
data class Note(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val timestamp: Long = System.currentTimeMillis()
)
```
---

## 👨‍💻 Developer

 Bishnu Prasad Sandha  
 Android Developer | Kotlin | Firebase

---

## 🪪 License
Free to use and modify for learning purposes.