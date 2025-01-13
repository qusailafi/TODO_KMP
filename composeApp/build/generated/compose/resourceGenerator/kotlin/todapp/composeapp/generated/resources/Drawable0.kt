@file:OptIn(org.jetbrains.compose.resources.InternalResourceApi::class)

package todapp.composeapp.generated.resources

import kotlin.OptIn
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@ExperimentalResourceApi
private object Drawable0 {
  public val compose_multiplatform: DrawableResource =
      org.jetbrains.compose.resources.DrawableResource(
        "drawable:compose_multiplatform",
          setOf(
            org.jetbrains.compose.resources.ResourceItem(setOf(),
          "drawable/compose-multiplatform.xml"),
          )
      )

  public val delete: DrawableResource = org.jetbrains.compose.resources.DrawableResource(
        "drawable:delete",
          setOf(
            org.jetbrains.compose.resources.ResourceItem(setOf(), "drawable/delete.xml"),
          )
      )

  public val star: DrawableResource = org.jetbrains.compose.resources.DrawableResource(
        "drawable:star",
          setOf(
            org.jetbrains.compose.resources.ResourceItem(setOf(), "drawable/star.xml"),
          )
      )
}

@ExperimentalResourceApi
internal val Res.drawable.compose_multiplatform: DrawableResource
  get() = Drawable0.compose_multiplatform

@ExperimentalResourceApi
internal val Res.drawable.delete: DrawableResource
  get() = Drawable0.delete

@ExperimentalResourceApi
internal val Res.drawable.star: DrawableResource
  get() = Drawable0.star
