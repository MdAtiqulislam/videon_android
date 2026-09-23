# Videon (videon_android)

Videon (华美剧院) — a native Android video-streaming app. Watch movies, TV shows, songs and comedy, download for offline viewing, and follow live channels.

## Features

- Video catalog: movies, TV series, songs and comedy clips
- Online streaming with offline download support
- Playlists and favorites
- Videon LIVE channels (news, music, lifestyle, events)
- Kids zone
- Push notifications (Firebase) and background job scheduling

## Tech Stack

- Native Android (Java, Gradle)
- YouTube Android Player API (`YouTubeAndroidPlayerApi`)
- Firebase (push / instance-ID services)
- Min/target SDK and dependencies managed in `app/build.gradle`

## Getting Started

1. Open the project in Android Studio.
2. Add your own `google-services.json` under `app/` (not committed).
3. Add your YouTube API key where `DeveloperKey` expects it (never commit keys).
4. Sync Gradle and Run:

```bash
./gradlew assembleDebug
```

## Project Structure

```
├── app/                        # Application module
│   ├── src/main/java/...       # Activities, utils, adapters, helpers
│   ├── src/main/res/           # Layouts, strings (incl. Chinese locale), assets
│   └── build.gradle            # App-level Gradle config (v2.0.1, vCode 21)
├── YouTubeAndroidPlayerApi/    # Player API library module
└── settings.gradle             # Included modules
```

## Notes

- Package: `com.huameijuyuan.app`
- No secrets, API keys or `google-services.json` are committed to this repository.
