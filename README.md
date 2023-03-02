# Tasks / Fixes
[ ] In horizontal mode, there should be a space of at least X dp between the conversion text 
    and the end of the screen
[ ] Increase the font size for the conversion text
[x] When phone rotates, ensure full screen is visible - implemented ScrollView
[x] When phone rotates, ensure conversion is retained - implemented ViewModel
[ ] (When there is more logic) Write unit tests for RecipesViewModel

# New Features
+ Improve UI (make prettier) while adhering to accessibility guidelines
+ Converter - Add tablespoon conversion (design idea: make spinner for 'cups' selection)
+ Converter - Add farenheit conversion
+ (EPIC) Recipes Collection - Enables user to note down and keep recipes in a local cache
+ (EPIC) Save Conversions - Enables user to save conversions to their recipes collection. 
   Must have provision to save multiple conversions to one recipe.
+ (EPIC) Export feature, to ensure recipes collection can be backed up if local storage fails. 
+ What is Glide and can it be used to insert images into our RecyclerView?