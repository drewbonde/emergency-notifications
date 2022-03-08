# Table of Contents

- [Emergency Notifications](#emergency-notifications)
- [Location and Interaction](#location-and-interaction)
- [Build processes](#build-processes)
- [Miscellaneous (Rename this Section)](#miscellaneous-rename-this-section)

## Emergency Notifications

- Emergency Notifications is an application that allows the end user to sign up for Oakland University's text/voice messaging alert system as part of its Comprehensive Emergency Communications Plan.
- Additionally, the end user can change their number(s) they have previously signed up with and/or remove their numbers from the alert system.

## Location and Interaction

- IM NOT EXACTLY SURE WHERE THIS WOULD LIVE IN UPORTAL SO THIS IS HERE UNTIL WE KNOW LOL
- For students, faculty, and staff.

## Build Processes

- Building with demo data
  - Navigate to src/main/react/public/index.html
	- On line 30, change `var IS_DEMO = false;` to `var IS_DEMO = true;`
	- Then run `npm start` in `src/main/react/src`
- Building in preparation for deployment
  - Ensure `var IS_DEMO = false;` in `src/main/react/public/index.html`
	- Run `./gradlew clean build` in the root of the project
  - To deploy, follow [this](https://code.oakland.edu/ea-developers/training/-/tree/master/06-mysail-development/portlets).
	> **Note:** You can also deploy the project to uPortal with demo data, if you wish to do so. However, personal entries in the test database ***will*** be updated upon clicking submit.

## Miscellaneous

SOFFIT URL: `http://localhost:8080/emergency-notifications-soffit/soffit/emergency-notifications-soffit`