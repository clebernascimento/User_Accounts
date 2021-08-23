package com.soft.useraccounts

import com.soft.useraccounts.data.model.AccountsEntity
import com.soft.useraccounts.ui.accountList.AccountAdapter
import com.soft.useraccounts.ui.accountList.AccountListFragment

import junit.framework.TestCase

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.ArrayList

class AccountAdapterTest : TestCase() {

    private lateinit var adapter: AccountAdapter

    @Before
    public override fun setUp() {
    }

    @After
    public override fun tearDown() {
    }

    @Test
    fun testAdapter() {
        val list = ArrayList<AccountsEntity>()
        adapter = AccountAdapter(list, AccountListFragment())
       Assert.assertEquals(0,adapter.itemCount)
    }
}