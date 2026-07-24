package com.example.kotlin_test

/**
 * ============================================================
 *  MINI-PROJECT: Unit Converter (Chuyển đổi đơn vị)
 * ============================================================
 *  Luyện các kiến thức Kotlin:
 *  - enum class
 *  - data class
 *  - class
 *  - function
 *  - when
 *  - collection (List, Map)
 *  - null safety (?, ?: , !!, let)
 * ============================================================
 */

// ----------------------------------------------------------
// 1) ENUM CLASS — danh sách cố định các đơn vị
// ----------------------------------------------------------
enum class LengthUnit(val symbol: String, val toMeter: Double) {
    MILLIMETER("mm", 0.001),
    CENTIMETER("cm", 0.01),
    METER("m", 1.0),
    KILOMETER("km", 1000.0),
    INCH("in", 0.0254),
    FOOT("ft", 0.3048)
}

enum class MassUnit(val symbol: String, val toKilogram: Double) {
    GRAM("g", 0.001),
    KILOGRAM("kg", 1.0),
    POUND("lb", 0.453592),
    OUNCE("oz", 0.0283495)
}

// ----------------------------------------------------------
// 2) DATA CLASS — chỉ chứa dữ liệu (auto equals/toString/copy)
// ----------------------------------------------------------
data class ConversionRequest(
    val value: Double,
    val from: String,   // ví dụ: "m", "kg"
    val to: String
)

data class ConversionResult(
    val originalValue: Double,
    val fromUnit: String,
    val convertedValue: Double,
    val toUnit: String
) {
    // Hàm trong data class
    fun pretty(): String =
        "$originalValue $fromUnit = ${"%.4f".format(convertedValue)} $toUnit"
}

// ----------------------------------------------------------
// 3) CLASS — logic nghiệp vụ (business logic)
// ----------------------------------------------------------
class UnitConverter {

    // --- Collection: Map (từ khóa → enum) ---
    private val lengthBySymbol: Map<String, LengthUnit> =
        LengthUnit.entries.associateBy { it.symbol }

    private val massBySymbol: Map<String, MassUnit> =
        MassUnit.entries.associateBy { it.symbol }

    // --- Collection: List tất cả symbol hỗ trợ ---
    fun supportedUnits(): List<String> {
        val lengths = lengthBySymbol.keys.toList()
        val masses = massBySymbol.keys.toList()
        return lengths + masses
    }

    /**
     * 4) FUNCTION — chuyển đổi chính
     * 5) NULL SAFETY — trả về null nếu đơn vị không hợp lệ
     *    (kiểu trả về: ConversionResult?)
     */
    fun convert(request: ConversionRequest): ConversionResult? {
        val from = request.from.lowercase()
        val to = request.to.lowercase()

        // Thử length trước
        val fromLength = lengthBySymbol[from]
        val toLength = lengthBySymbol[to]
        if (fromLength != null && toLength != null) {
            val meters = request.value * fromLength.toMeter
            val result = meters / toLength.toMeter
            return ConversionResult(request.value, from, result, to)
        }

        // Thử mass
        val fromMass = massBySymbol[from]
        val toMass = massBySymbol[to]
        if (fromMass != null && toMass != null) {
            val kg = request.value * fromMass.toKilogram
            val result = kg / toMass.toKilogram
            return ConversionResult(request.value, from, result, to)
        }

        // Không cùng loại đơn vị hoặc không tìm thấy → null
        return null
    }

    /**
     * Overload: nhận String rồi parse
     * Luyện: when + null safety
     */
    fun convert(valueText: String?, from: String?, to: String?): ConversionResult? {
        // Elvis operator ??: nếu null thì dùng giá trị mặc định / dừng sớm
        val raw = valueText?.trim() ?: return null
        val fromUnit = from?.trim()?.lowercase() ?: return null
        val toUnit = to?.trim()?.lowercase() ?: return null

        // toDoubleOrNull() trả về Double? — an toàn hơn toDouble()
        val value = raw.toDoubleOrNull() ?: return null

        return convert(ConversionRequest(value, fromUnit, toUnit))
    }

    /**
     * 6) WHEN — phân loại kết quả để hiển thị thông báo
     */
    fun describe(result: ConversionResult?): String {
        return when {
            result == null -> "Không thể chuyển đổi (đơn vị sai hoặc khác loại)."
            result.convertedValue == 0.0 -> "Kết quả bằng 0."
            result.convertedValue > 1_000_000 -> "Số rất lớn: ${result.pretty()}"
            else -> result.pretty()
        }
    }

    /**
     * when với enum — ví dụ chọn "nhóm" đơn vị
     */
    fun categoryOf(symbol: String?): String {
        // ?.let { } chỉ chạy khi không null
        return symbol?.lowercase()?.let { key ->
            when {
                lengthBySymbol.containsKey(key) -> "Độ dài (length)"
                massBySymbol.containsKey(key) -> "Khối lượng (mass)"
                else -> "Không xác định"
            }
        } ?: "Chưa nhập đơn vị"   // Elvis: nếu symbol null
    }
}

// ----------------------------------------------------------
// DEMO — gọi thử trong Logcat (xem MainActivity / FirstFragment)
// ----------------------------------------------------------
object UnitConverterDemo {

    fun runSamples(): List<String> {
        val converter = UnitConverter()
        val lines = mutableListOf<String>()

        lines += "=== Đơn vị hỗ trợ ==="
        lines += converter.supportedUnits().joinToString(", ")

        // Case 1: hợp lệ
        val r1 = converter.convert("100", "cm", "m")
        lines += converter.describe(r1)

        // Case 2: data class + convert
        val r2 = converter.convert(ConversionRequest(2.0, "kg", "g"))
        lines += converter.describe(r2)

        // Case 3: null safety — input null / sai
        val r3 = converter.convert(null, "m", "km")
        lines += converter.describe(r3)

        val r4 = converter.convert("abc", "m", "km") // không phải số
        lines += converter.describe(r4)

        val r5 = converter.convert("10", "m", "kg") // khác loại
        lines += converter.describe(r5)

        // Case 4: when + category
        lines += "m  → ${converter.categoryOf("m")}"
        lines += "kg → ${converter.categoryOf("kg")}"
        lines += "?? → ${converter.categoryOf(null)}"

        // Case 5: copy() của data class
        r2?.let { ok ->
            val rounded = ok.copy(convertedValue = "%.2f".format(ok.convertedValue).toDouble())
            lines += "copy(): ${rounded.pretty()}"
        }

        return lines
    }
}
