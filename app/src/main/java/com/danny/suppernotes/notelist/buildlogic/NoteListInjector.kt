package com.danny.suppernotes.notelist.buildlogic

import com.danny.suppernotes.notelist.NoteListAdapter
import com.danny.suppernotes.notelist.NoteListLogic
import com.danny.suppernotes.notelist.NoteListView
import com.danny.suppernotes.notelist.NoteListViewModel


class NoteListInjector(application: Application) : AndroidViewModel(application) {
    init {
        FirebaseApp.initializeApp(application)
    }

    private val anonNoteDao: AnonymousNoteDao by lazy {
        AnonymousNoteDatabase.getInstance(application).roomNoteDao()
    }

    private val regNoteDao: RegisteredNoteDao by lazy {
        RegisteredNoteDatabase.getInstance(application).roomNoteDao()
    }

    private val transactionDao: RegisteredTransactionDao by lazy {
        RoomRegisteredTransactionDatabase.getInstance(application).roomTransactionDao()
    }

    //For non-registered user persistence
    private val localAnon: ILocalNoteRepository by lazy {
        RoomLocalAnonymousRepositoryImpl(anonNoteDao)
    }

    //For registered user remote persistence (Source of Truth)
    private val remotePrivate: IRemoteNoteRepository by lazy {
        FirestorePrivateRemoteNoteImpl()
    }

    //For registered user remote persistence (Source of Truth)
    private val remotePublicRepo: IPublicNoteRepository by lazy {
        FirestoreRemoteNoteImpl
    }

    //For registered user local persistience (cache)
    private val cacheReg: ILocalNoteRepository by lazy {
        RoomLocalCacheImpl(regNoteDao)
    }

    //For registered user remote persistence (Source of Truth)
    private val remotePrivateRepo: IRemoteNoteRepository by lazy {
        RegisteredNoteRepositoryImpl(remotePrivate, cacheReg)
    }

    //For registered user local persistience (cache)
    private val transactionReg: ITransactionRepository by lazy {
        RoomTransactionRepositoryImpl(transactionDao)
    }

    //For user management
    private val auth: IAuthRepository by lazy {
        FirebaseAuthRepositoryImpl()
    }


    private lateinit var logic: NoteListLogic

    fun buildNoteListLogic(view: NoteListView): NoteListLogic {
        logic = NoteListLogic(
            DispatcherProvider,
            NoteServiceLocator(localAnon, remotePrivateRepo, transactionReg, remotePublicRepo),
            UserServiceLocator(auth),
            ViewModelProviders.of(view)
                .get(NoteListViewModel::class.java),
            NoteListAdapter(),
            view,
            AnonymousNoteSource(),
            RegisteredNoteSource(),
            PublicNoteSource(),
            AuthSource()
        )

        view.setObserver(logic)

        return logic
    }
}