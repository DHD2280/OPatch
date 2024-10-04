package io.github.suqi8.opatch

import android.content.Context
import android.graphics.Rect
import android.view.ViewTreeObserver
import android.view.WindowManager
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.BlendModeColorFilter
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import com.highcapable.yukihookapi.YukiHookAPI
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Box
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.LazyColumn
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SearchBar
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.Search
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.createRipple

@Composable
fun Main_Function(
    topAppBarScrollBehavior: ScrollBehavior,
    navController: NavController,
    padding: PaddingValues
) {
    val context = LocalContext.current
    val features = listOf(
        Feature(stringResource(R.string.package_manager_services), null, "Fun_android_package_manager_services"),
        Feature(stringResource(R.string.downgr), stringResource(R.string.downgr_summary), "Fun_android_package_manager_services"),
        Feature(stringResource(R.string.authcreak), stringResource(R.string.authcreak_summary), "Fun_android_package_manager_services"),
        Feature(stringResource(R.string.digestCreak), stringResource(R.string.digestCreak_summary), "Fun_android_package_manager_services"),
        Feature(stringResource(R.string.UsePreSig), stringResource(R.string.UsePreSig_summary), "Fun_android_package_manager_services"),
        Feature(stringResource(R.string.enhancedMode), stringResource(R.string.enhancedMode_summary), "Fun_android_package_manager_services"),
        Feature(stringResource(R.string.bypassBlock), stringResource(R.string.bypassBlock_summary), "Fun_android_package_manager_services"),
        Feature(stringResource(R.string.disable_verification_agent_title), stringResource(R.string.disable_verification_agent_summary), "Fun_android_package_manager_services"),
        Feature(stringResource(R.string.status_bar_clock), null, "Fun_com_android_systemui_status_bar_clock"),
        Feature(
            stringResource(R.string.clock_style_title),
            stringResource(R.string.clock_style_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.clock_size),
            stringResource(R.string.clock_size_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.clock_update_time_title),
            stringResource(R.string.clock_update_time_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.show_years_title),
            stringResource(R.string.show_years_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.show_month_title),
            stringResource(R.string.show_month_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.show_day_title),
            stringResource(R.string.show_day_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.show_week_title),
            stringResource(R.string.show_week_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.show_cn_hour_title),
            stringResource(R.string.show_cn_hour_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.showtime_period_title),
            stringResource(R.string.showtime_period_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.show_seconds_title),
            stringResource(R.string.show_seconds_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.show_millisecond_title),
            stringResource(R.string.show_millisecond_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.hide_space_title),
            stringResource(R.string.hide_space_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.dual_row_title),
            stringResource(R.string.dual_row_summary),
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.alignment),null,
            "Fun_com_android_systemui_status_bar_clock"
        ),
        Feature(
            stringResource(R.string.clock_format),null,
            "Fun_com_android_systemui_status_bar_clock"
        )
    )
    var miuixSearchValue by remember { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    var isKeyboardVisible by remember { mutableStateOf(false) }

    DisposableEffect(context) {
        val rootView = (context as MainActivity).window.decorView
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val insets = ViewCompat.getRootWindowInsets(rootView)
            isKeyboardVisible = insets?.isVisible(WindowInsetsCompat.Type.ime()) == true
        }

        rootView.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            rootView.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    // 过滤符合搜索条件的功能
    val filteredFeatures = features.filter {
        it.nickname.contains(miuixSearchValue, ignoreCase = true) ||
                it.description?.contains(miuixSearchValue, ignoreCase = true) ?: false
    }

    Column(
        modifier = Modifier
            .padding(top = padding.calculateTopPadding())
            .fillMaxSize()
    ) {
        SearchBar(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
            inputField = {
                InputField(
                    query = miuixSearchValue,
                    onQueryChange = { miuixSearchValue = it },
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    label = stringResource(R.string.Search),
                    leadingIcon = {
                        Image(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            imageVector = MiuixIcons.Search,
                            colorFilter = BlendModeColorFilter(
                                MiuixTheme.colorScheme.onSurfaceContainer,
                                BlendMode.SrcIn
                            ),
                            contentDescription = stringResource(R.string.Search)
                        )
                    }
                )
            },
            outsideRightAction = {
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable {
                            expanded = false
                            miuixSearchValue = ""
                        },
                    text = stringResource(R.string.cancel),
                    color = MiuixTheme.colorScheme.primary
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = (if (isKeyboardVisible) 6 else 65).dp, top = 6.dp)
            ) {
                LazyColumn(topAppBarScrollBehavior = topAppBarScrollBehavior) {
                    if (filteredFeatures.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "空空如也~")
                            }
                        }
                    }

                    filteredFeatures.forEach { feature ->
                        item {
                            AnimatedVisibility(
                                visible = true,
                                enter = fadeIn(animationSpec = tween(durationMillis = 300)),
                                exit = fadeOut(animationSpec = tween(durationMillis = 300))
                            ) {
                                BasicComponent(
                                    title = highlightMatches(feature.nickname, miuixSearchValue),
                                    summary = feature.description?.let { highlightMatches(it, miuixSearchValue) },
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        miuixSearchValue = feature.nickname
                                        expanded = false
                                        navController.navigate(feature.page)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }

        if (expanded) {
            // 如果 expanded 为 true，则显示搜索结果
        } else {
            // 如果 expanded 为 false，则显示 Card
            LazyColumn(Modifier.fillMaxSize(), topAppBarScrollBehavior = topAppBarScrollBehavior) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 6.dp)
                    ) {
                        Column() {
                            FunctionApp("android", "Fun_android", navController)
                            FunctionApp("com.android.systemui", "Fun_com_android_systemui", navController)
                        }
                    }
                    Spacer(Modifier.size(65.dp))
                }
            }
        }
    }
}

// 高亮匹配内容的函数
fun highlightMatches(text: String, query: String): AnnotatedString {
    if (query.isBlank()) return AnnotatedString(text) // 如果查询为空，则返回原始文本

    val regex = Regex("($query)", RegexOption.IGNORE_CASE) // 匹配查询字符串的正则表达式
    val annotatedStringBuilder = AnnotatedString.Builder()

    var lastIndex = 0
    for (match in regex.findAll(text)) {
        // 添加匹配前的文本
        annotatedStringBuilder.append(text.substring(lastIndex, match.range.first))
        // 添加高亮部分
        annotatedStringBuilder.pushStyle(SpanStyle(color = Color.Red))
        annotatedStringBuilder.append(match.value)
        annotatedStringBuilder.pop()
        lastIndex = match.range.last + 1
    }
    // 添加剩余的文本
    annotatedStringBuilder.append(text.substring(lastIndex))

    return annotatedStringBuilder.toAnnotatedString()
}

@Composable
fun BasicComponent(
    modifier: Modifier = Modifier,
    insideMargin: DpSize? = null,
    title: AnnotatedString? = null, // 修改为 AnnotatedString
    titleColor: Color = MiuixTheme.colorScheme.onSurface,
    summary: AnnotatedString? = null, // 修改为 AnnotatedString
    summaryColor: Color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
    leftAction: @Composable (() -> Unit?)? = null,
    rightActions: @Composable RowScope.() -> Unit = {},
    onClick: (() -> Unit)? = null,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = null
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val indication = indication ?: createRipple()
    val insideMargin = remember { insideMargin } ?: remember { DpSize(16.dp, 16.dp) }
    val paddingModifier = remember(insideMargin) {
        Modifier.padding(horizontal = insideMargin.width, vertical = insideMargin.height)
    }

    Row(
        modifier = if (onClick != null) {
            modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = indication
                ) {
                    onClick.invoke()
                }
        } else {
            modifier
        }
            .fillMaxWidth()
            .then(paddingModifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        leftAction?.let {
            Box(
                modifier = Modifier.padding(end = 16.dp)
            ) {
                it()
            }
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            title?.let {
                Text(
                    text = it,
                    color = titleColor,
                    fontWeight = FontWeight.Medium
                )
            }
            summary?.let {
                Text(
                    text = it,
                    color = summaryColor, // 这里使用传入的颜色
                    fontSize = MiuixTheme.textStyles.title.fontSize,
                )
            }
        }
        Box(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                content = rightActions
            )
        }
    }
}

@Composable
fun FunctionApp(packageName: String, activityName: String, navController: NavController) {
    GetAppIconAndName(packageName = packageName) { appName, icon ->
        if (appName != "noapp") {
            Row(modifier = Modifier
                .clickable {
                    navController.navigate(activityName)
                }
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Card(color = if (YukiHookAPI.Status.isModuleActive) MiuixTheme.colorScheme.primary else MaterialTheme.colorScheme.errorContainer,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                        .drawColoredShadow(
                            if (YukiHookAPI.Status.isModuleActive) MiuixTheme.colorScheme.primary else MaterialTheme.colorScheme.errorContainer,
                            1f,
                            borderRadius = 13.dp,
                            shadowRadius = 7.dp,
                            offsetX = 0.dp,
                            offsetY = 0.dp,
                            roundedRect = false
                        )) {
                    Image(bitmap = icon, contentDescription = "App Icon", modifier = Modifier
                        .size(48.dp))
                }
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(start = 16.dp)) {
                    Text(text = appName)
                    Text(text = packageName)
                }
            }
        } else {
            Text(text = "$packageName 没有安装", modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp))
        }
    }
}

data class Feature(
    val nickname: String,
    val description: String?,
    val page: String
)
