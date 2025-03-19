package ghazimoradi.soheil.divar.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object AppTheme {
    val colors: AppColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

private val DarkColorScheme = AppColor(
    primaryColor = ButtonBlue,
    itemColor = EerieBlackDark,
    iconColor = BrightGray,
    textColor = TextColorDark,
    hintColor = SilverChalice,
    titleColor = TitleColorDark,
    backgroundColor = VampireBlack,
    errorColor = ErrorColorDark,
    disableColor = DisableColorDark,
    successColor = SuccessColorDark
)

private val LightColorScheme = AppColor(
    primaryColor = DenimBlue,
    itemColor = AliceBlue,
    iconColor = SmokyBlack,
    textColor = EerieBlack,
    hintColor = GraniteGray,
    titleColor = RaisinBlack,
    backgroundColor = BackgroundColorLight,
    errorColor = ErrorColorLight,
    disableColor = DisableColorLight,
    successColor = SuccessColorLight
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalColor provides colorScheme,
        LocalDimensions provides AppTheme.dimensions,
        LocalShapes provides AppTheme.shapes,
        LocalTypography provides AppTheme.typography
    ) {
        content()
    }
}