# SupperNotes
Its a work in progress A Note keeping Android App With Animations And room persistence

Its Basically An adroid App That am still working on A NoteKeeping App

SpaceNotes is a Kotlin based Android Application, which was built with best practices an innovation in mind. The app uses Coroutines for concurrency and cross-module/boundary communication, a Clean Domain Layer to allow the application to work properly across multiple platforms, and a few of my favourite APIs from Android Architecture Component and Firebase.


This feature displays whatever Notes are currently available based on the user's status, such as: Anonymous, Registered Private, Registered Public
INoteListContract specifies the different interactions between classes and the events which may occur in this particular feature
NoteListActivity is a feature level “container”, within which these different things are deployed in to (it is also the entry point of the feature)
NoteListLogic is the “decision maker” of the feature, which handles the events and interactions specified in the contract (this kind of class is the most important to test)
NoteListView contains logic and bindings to the user interface
NoteListAdapter contains a decoupled RecyclerView.ListAdapter w/ DiffUtil
NoteListViewModel contains the most recent data which has been returned from the “backend” of the application (or data which is passed into the feature via navigation), and persists this data so that the logic class or view does not need to (if they did, it would break the separation of concerns)
NoteListInjector: Build logic (Dpependency Injection Implementation) for this feature.

# NoteDetailFeature
This feature allows the User to view, update, create, and delete a Note. Data is stored in various local/remote datasources based on whether the user is or isn't logged in, and if they are in public or private mode.

INoteDetailContract specifies the different interactions between classes and the events which may occur in this particular feature
NoteDetailActivity is a feature level “container”, within which these different things are deployed in to (it is also the entry point of the feature)
NoteDetailLogic is the “decision maker” of the feature, which handles the events and interactions specified in the contract (this kind of class is the most important to test)
NoteDetailView contains logic and bindings to the user interface
NoteDetailViewModel contains the most recent data which has been returned from the “backend” of the application (or data which is passed into the feature via navigation), and persists this data so that the logic class or view does not need to (if they did, it would break the separation of concerns)
NoteDetailInjector: Build logic (Dpependency Injection Implementation) for this feature.

# Login feature

This feature allows the User to authenticate with GoogleSignIn; which is currently the only supported sign in function. No passwords or in-app Sign up is required.

Note: I normally advocate against using Activities as Views, but I ran in to a tight-coupling problem with GoogleSignIn API (which requires you to override Activity.onActivityResult(...). Given this tight coupling, and the simplicity of this feature (it only has two buttons including the toolbar), I decided to just use the Activity as a pragmatic decision.

ILoginContract specifies the different interactions between classes and the events which may occur in this particular feature
LoginActivity acts as the View and Container in this feature (for reasons mentioned above)
LoginLogic is the “decision maker” of the feature, which handles the events and interactions specified in the contract (this kind of class is the most important to test)
LoginResult Wrapper for when GoogleSignInProviders does it's thing (logging a User In)
LoginInjector: Build logic (Dpependency Injection Implementation) for this feature.

# Common

Navigation.kt: Contains Top-level functions for starting each feature with the appropriate arguments.
Constants.kt: Contains messages and keys for front end Android
BaseLogic.kt: Abstract class for Logic classes. Could be optimized, currently just contains a DispatcherProvider (for Coroutines) as a property, and a Job object for keeping track and disposing in-flight coroutines.
AndroidExt.kt: Some handy Extensions functions for front end Android
