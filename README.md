# Recipe Sharing App
This is a recipe-sharing app built as part of a college course project. The app allows users to upload and share their recipes with others. Users can follow each other and track recipes as soon as they are uploaded.

#### Team Members
This project was developed by a team of four:
* Benjamin Dlakic
* Tarik Karahodzic
* Sulejman Muhic
* Naim Pjanic

#### Features
Upload Recipes: Users can easily upload their own recipes, including ingredients, instructions, and photos.
Follow Users: Users can follow others to stay updated on new recipes.
Real-time Updates: Get notified and track recipes as soon as they are uploaded by users you follow.
Tech Stack
Frontend: React Native
Backend: Room Database

#### Installation
To install the app, follow these steps:

#### Clone the repository:
git clone <repository-url>

#### Navigate to the project directory:
cd recipe-sharing-app

#### Build the AAB file: Use the terminal to generate the Android App Bundle (AAB) file:
./gradlew bundleRelease

#### Convert AAB to APK: 
Once the AAB file is generated, use the following command to create an APK file:
java -jar bundletool-all-1.0.0.jar build-apks --bundle=path-to-aab.aab --output=output-dir --mode=universal
Replace path-to-aab.aab with the actual path to your AAB file and output-dir with the desired output directory for the APK.

Install the APK: Transfer the APK to your Android device and install it.

### Usage
Once installed, users can:
Create a profile
Upload their favorite recipes
Follow other users and explore new recipes
Get real-time updates on new recipes from users they follow
Contribution
Contributions are welcome! Please fork this repository and submit a pull request.

License
This project is licensed under the MIT License.
