package com.danny.suppernotes.notelist

import android.provider.ContactsContract


class NoteDiffUtilCallback : DiffUtil.ItemCallback<ContactsContract.CommonDataKinds.Note>(){
    override fun areItemsTheSame(oldItem: ContactsContract.CommonDataKinds.Note, newItem: Note): Boolean {
        return oldItem.creationDate == newItem.creationDate
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.creationDate == newItem.creationDate
    }

}