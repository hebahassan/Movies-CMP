# Movies-CMP
Compose Multi-platform movies app for Android and iOS 

# Architecture
Module Structure
```
composeApp/          → App entry point, navigation, DI initialization
core/
  ├── network/       → Ktor client setup, API constants
  ├── ui/            → Theme, colors, typography, shared composables
  └── common/        → Shared utilities
feature/
  ├── home/          → Home screen (data/domain/presentation packages)
  └── details/       → Details screen (data/domain/presentation packages)
iosApp/              → iOS entry point
```
Multi-module architecture enforces clear layer boundaries, each feature module contains data, domain, and presentation as packages to keep feature isolated and for Gradle to only build what changed, making development loop much faster.

# Build & Run
Android: Select composeApp configuration -> Run
iOS: Select iosApp configuration -> Run / Open iosApp/iosApp.xcodeproj in Xcode -> Run

# API key setup
1- For Android: Add your access token to `local.properties`
```properties
tmdb.access.token=YOUR_ACCESS_TOKEN
```
2- For iOS: Add your access token to `iosApp/Secrets.plist`
```xml
<key>accessToken</key>
<string>YOUR_ACCESS_TOKEN</string>
```
3- sync & run

# Trade-offs
- Packages over modules inside features: Each feature is a Gradle module, but data, domain and presentation are packages inside. This balances clean architecture with KMP Gradle complexity

# CMP Limitations
- SVG: Android doesn't support SVG in compose resources natively, so shared SVG assets are loaded through Coil
- Context for share sheet: Ideally, the share intent should use Activity context via `LocalContext.current` in Compose but since passing Activity context would break the multiplatform pattern, used Application context with `FLAG_ACTIVITY_NEW_TASK` as a workaround
