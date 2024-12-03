package com.outsystems.experts.capacitorsmartwatchplugin

import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.annotation.CapacitorPlugin
import com.getcapacitor.PluginMethod
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.MessageClient
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

private const val SMARTWATCH_CAPABILITY = "outsystems_smartwatch_app"
private const val ACTION_MESSAGE_DATA = "ACTION_MESSAGE_DATA"

@CapacitorPlugin(name = "Smartwatch")
class SmartwatchPlugin : Plugin() {

    private lateinit var messageClient: MessageClient
    private lateinit var capabilityClient: CapabilityClient
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main) + SupervisorJob()

    override fun load() {
        super.load()
        messageClient = Wearable.getMessageClient(context)
        capabilityClient = Wearable.getCapabilityClient(context)
    }

    @PluginMethod
    fun sendMessage(call: PluginCall) {
        val value = call.getString("value")
        scope.launch {
            sendMessageWear(value.toString(), call)
        }
    }

    /**
     * Send data to Smartwatch using BLE
     */
    private suspend fun sendMessageWear(value: String, call: PluginCall) {
        try {
            val capabilityInfo: CapabilityInfo = withContext(Dispatchers.IO) {
                capabilityClient.getCapability(SMARTWATCH_CAPABILITY, CapabilityClient.FILTER_ALL).await()
            }

            val connectedNodes = capabilityInfo.nodes
            if (connectedNodes.isNotEmpty()) {
                val node: Node = connectedNodes.iterator().next()

                try {
                    withContext(Dispatchers.IO) {
                        messageClient.sendMessage(node.id, ACTION_MESSAGE_DATA, value.toByteArray()).await()
                    }
                    call.resolve()
                } catch (e: Exception) {
                    call.reject(e.message.toString())
                }
            } else {
                call.reject("No nodes found with the required capability.")
            }
        } catch (e: Exception) {
            call.reject("Failed to get capability: ${e.message}")
        }
    }
}