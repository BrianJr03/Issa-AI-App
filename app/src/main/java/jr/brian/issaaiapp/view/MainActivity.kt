package jr.brian.issaaiapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.asadullah.Configuration
import com.asadullah.OpenAI
import com.asadullah.api.request.CreateEditRequest
import jr.brian.issaaiapp.view.ui.pages.ChatPage
import jr.brian.issaaiapp.view.ui.theme.IssaAIAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content))
        { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
        setContent {
            IssaAIAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ChatPage()
                    val s = listOf<Int>()
                    openAI()
                }
            }
        }
    }

    fun openAI() {
        val openAISecretKey: String = "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"
        val openAI = OpenAI(configuration = Configuration(openAISecretKey))
        CoroutineScope(Dispatchers.IO).launch {

            val edit = openAI.createEdit(
                request = CreateEditRequest(
                    modelId = 1,
                    input = "What day of the wek is it?",
                    instruction = "Fix the spelling mistakes")
            )
            println(edit.data?.usage)

//            CoroutineScope(Dispatchers.IO).launch {
//                val response = openAI.getModels()
//                val models = response.data?.data
//                var num = 1
//                models?.forEach { model ->
//                    println("$num -> ${model.id}")
//                    num++
//                }
//            }
        }
    }
}