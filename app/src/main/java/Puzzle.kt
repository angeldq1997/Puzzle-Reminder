import android.widget.ImageView
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonGroup

data class Puzzle(
    val name: String,
    val initialImage : ImageView,
    val solvedImage : ImageView,
    val buttonGroup : MaterialButtonGroup,
    val button1 : MaterialButton,
    val button2 : MaterialButton,
    val button3 : MaterialButton,
    val button4 : MaterialButton
)