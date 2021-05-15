package dev.adamgyulavari.quickgpaysettings

import android.content.Intent
import android.graphics.drawable.Icon
import android.nfc.NfcAdapter
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService


class QuickPaySettings : TileService() {

    override fun onStartListening() {
        super.onStartListening()
        var tile = qsTile
        var adapter = NfcAdapter.getDefaultAdapter(this)
        if(adapter != null && adapter.isEnabled) {
            tile.state = Tile.STATE_ACTIVE
            tile.icon = Icon.createWithResource(this, R.drawable.ic_action_credit_card_dark)
        } else {
            tile.state = Tile.STATE_INACTIVE
            tile.icon = Icon.createWithResource(this, R.drawable.ic_action_credit_card_light)
        }
        tile.updateTile()
    }

    override fun onClick() {
        var intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setClassName("com.google.android.gms", "com.google.android.gms.tapandpay.settings.TapAndPaySettingsActivity")
        startActivityAndCollapse(intent)
    }
}