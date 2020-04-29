package com.danny.suppernotes.notedetail

import android.provider.ContactsContract
import androidx.lifecycle.Observer


interface INoteDetailContract {

    interface View {
        fun setBackgroundImage(imageUrl: String)
        fun setDateLabel(date: String)
        fun setNoteBody(content: String)
        fun setObserver(observer: Observer<NoteDetailEvent>)
        fun hideBackButton()
        fun getNoteBody(): String
        fun getTime(): String
        fun restartFeature()
        fun showMessage(message: String)
        fun showConfirmDeleteSnackbar()
        fun startListFeature()
    }

    interface ViewModel {
        fun setIsPrivateMode(isPrivateMode: Boolean)

        fun getIsPrivateMode(): Boolean

        fun setNoteState(note: ContactsContract.CommonDataKinds.Note)

        fun getNoteState(): ContactsContract.CommonDataKinds.Note?

        fun setId(id: String)

        fun getId(): String?
    }

}

sealed class NoteDetailEvent {
    object OnDoneClick : NoteDetailEvent()
    object OnDeleteClick : NoteDetailEvent()
    object OnDeleteConfirmed : NoteDetailEvent()
    object OnBackClick : NoteDetailEvent()
    object OnStart : NoteDetailEvent()
    object OnBind : NoteDetailEvent()
    object OnDestroy : NoteDetailEvent()
}