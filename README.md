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
