package com.rikonardo.mycatbadges.spigot

import com.rikonardo.catbadge.badge.*
import dev.virefire.yok.Yok
import java.awt.Color
import javax.imageio.ImageIO

@RegisterBadge(
    category = "SpigotMC",
    name = "Total Downloads",
    description = "Total downloads of SpigotMC resource",
)
@Suppress("DuplicatedCode")
class SpigotDownloads : BadgeProvider {
    @BadgeProperty("resource_id")
    lateinit var resourceId: String

    override fun make(): Badge {
        val res = Yok.get("https://api.spigotmc.org/simple/0.2/index.php") {
            query = mapOf(
                "action" to "getResource",
                "id" to resourceId
            )
        }
        if (res.status == 404) throw BadgeError("Resource not found")
        if (res.status != 200) throw BadgeError("SpigotMC responded with status ${res.status}")
        val downloads =
            res.body.json.silent["stats"]["downloads"].string ?: throw BadgeError("Could not find downloads")
        return Badge(
            label = "Downloads",
            message = downloads,
            icon = ImageIO.read(javaClass.getResourceAsStream("/com/rikonardo/catbadge/badges/spigot-white.png")),
            messageBackgroundColor = Color(0xED8106)
        )
    }
}
