package es.niadecode.common.repository

import com.gikk.twirk.TwirkBuilder
import com.gikk.twirk.events.TwirkListener
import com.gikk.twirk.types.twitchMessage.TwitchMessage
import com.gikk.twirk.types.users.TwitchUser
import es.niadecode.common.model.GameParticipation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class TwitchRepository(
    private val externalScope: CoroutineScope,
) {

    private val _participationFlow = MutableSharedFlow<GameParticipation>(replay = 0)
    val participationFlow: SharedFlow<GameParticipation> = _participationFlow


    private val twirk = TwirkBuilder("niadecode", "justinfan5555", "kappa")
        //.setVerboseMode(true)
        .build().apply {
            connect()
            addIrcListener(object : TwirkListener {
                override fun onPrivMsg(sender: TwitchUser?, message: TwitchMessage?) {
                    println("${sender?.displayName}: ${message?.content}")
                    onTwitchMessageReceived(sender?.displayName.orEmpty(), message?.content.orEmpty())
                }
            })
        }

    fun close() {
        twirk.close()
    }

    private fun onTwitchMessageReceived(userName: String, message: String) {
        try {
            val number = Integer.parseInt(message)
            externalScope.launch {
                _participationFlow.emit(GameParticipation(userName, number))
            }

        } catch (e: NumberFormatException) {
            return
        }

    }


}
