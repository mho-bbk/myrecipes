# Tasks / Fixes

## Feature: Converter
[ ] In horizontal mode, there should be a space of at least X dp between the conversion text 
    and the end of the screen
[ ] Increase the font size for the conversion text
[x] When phone rotates, ensure full screen is visible - implemented ScrollView
[x] When phone rotates, ensure conversion is retained - implemented ViewModel

## Feature: My Recipes Collection
[ ] (When there is more logic) Write unit tests for RecipesViewModel
[ ] Fix RecyclerView UI so that it is smaller and more intuitive to the screen/to scroll
[ ] Could add image to recyclerview per recipe

# New Features
+ Improve UI (make prettier) while adhering to accessibility guidelines
+ Converter - Add tablespoon conversion (design idea: make spinner for 'cups' selection)
+ Converter - Add farenheit conversion
+ (EPIC) Recipes Collection - Enables user to note down and keep recipes in a local cache
+ (EPIC) Save Conversions - Enables user to save conversions to their recipes collection. 
   Must have provision to save multiple conversions to one recipe.
+ (EPIC) Export feature, to ensure recipes collection can be backed up if local storage fails. 
+ What is Glide and can it be used to insert images into our RecyclerView?

# References
In the building of this app, the following resources were referenced:

## MVVM and Room (+ RecyclerView)
+ Philip Lackner, https://www.youtube.com/playlist?list=PLQkwcJG4YTCRF8XiCRESq1IFFW8COlxYJ
+ Google Codelabs, https://github.com/googlecodelabs/android-room-with-a-view/tree/kotlin