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
    itemColor = DarkCharcoal,
    iconColor = BrightGray,
    textColor = White,
    hintColor = SilverChalice,
    titleColor = White,
    backgroundColor = RaisinBlack,
    errorColor = Razzmatazz,
    disableColor = SonicSilver,
    successColor = Apple
)

private val LightColorScheme = AppColor(
    primaryColor = DenimBlue,
    itemColor = Lotion,
    iconColor = EerieBlack,
    textColor = EerieBlack,
    hintColor = GraniteGray,
    titleColor = RaisinBlack,
    backgroundColor = White,
    errorColor = Razzmatazz,
    disableColor = SonicSilver,
    successColor = Apple
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