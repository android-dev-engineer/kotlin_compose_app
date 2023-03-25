# Kotlin Compose App

## [KtLint](https://github.com/JLLeitschuh/ktlint-gradle)

- Static code analysis tool for Kotlin which `code style`, `code formatting` and `code convention`.
- Widely used in `Android Projects``
- Keep your code consistent.

# Steps to integrate with KtLint

1. Add [KtLint Gradle Plugin](https://github.com/JLLeitschuh/ktlint-gradle#ktlint-plugin) at the `build.gradle` project level.
2. Apply KtLint Plugin to [modules/subprojects](https://github.com/JLLeitschuh/ktlint-gradle#applying-to-subprojects).
3. Define KtLint [configuration](https://github.com/JLLeitschuh/ktlint-gradle#configuration) such as [version](https://pinterest.github.io/ktlint), `android`, `verbose`, `reporters`, `filters`.
4. Check Kotlin issues: `./gradlew ktlintCheck`.
5. Create an `.editorconfig` file to tweak/adjust [rules](https://pinterest.github.io/ktlint/rules/configuration-ktlint) in th root folder.
6. Fix Kotlin issues: `./gradlew ktlintFormat` you agree with.

Other options are [available](https://pinterest.github.io/ktlint/install/integrations/#gradle-integration)