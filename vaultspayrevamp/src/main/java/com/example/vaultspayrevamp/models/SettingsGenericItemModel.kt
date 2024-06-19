package com.example.vaultspayrevamp.models

import com.example.vaultspayrevamp.R

data class SettingsGenericItemModel(
    val icon: Int,
    val title: String,
    val detail: String? = null,
    val itemType: ItemType
)

enum class ItemType {
    MoveForward,
    Switch,
    Theme,
    Language
}

var accountSettingsList = listOf(
    SettingsGenericItemModel(
        icon = R.drawable.account_icon_with_bg,
        title = "Account Information",
        itemType = ItemType.MoveForward
    ),
    SettingsGenericItemModel(
        icon = R.drawable.wallets_with_bg,
        title = "Your Wallets",
        itemType = ItemType.MoveForward
    ),
    SettingsGenericItemModel(
        icon = R.drawable.notification_with_bg,
        title = "Notification Methods",
        detail = "Set secondary email or phone numbers to receive notifications",
        itemType = ItemType.MoveForward
    ),
    SettingsGenericItemModel(
        icon = R.drawable.report_issue_with_bg,
        title = "Saved Cards",
        itemType = ItemType.MoveForward
    ),
    SettingsGenericItemModel(
        icon = R.drawable.withdraw_methods_icon_with_bg,
        title = "Withdraw Methods",
        detail = "Edit or remove your withdraw methods",
        itemType = ItemType.MoveForward
    ),
    SettingsGenericItemModel(
        icon = R.drawable.devices_icon_with_bg,
        title = "Your Devices",
        detail = "See all the devices used for logging into your account",
        itemType = ItemType.MoveForward
    ),
)
