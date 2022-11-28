package com.juraj.stocksbrowser.ui.home.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.juraj.stocksbrowser.ui.home.screen.DeltaIndicator
import com.juraj.stocksbrowser.ui.home.screen.ListItem

@Preview(showBackground = true, widthDp = 400)
@Composable
fun InstrumentListItem2(
    @PreviewParameter(FakeInstrumentItems2::class) instrumentItem: ListItem.InstrumentItem,
    onClick: (ListItem.InstrumentItem) -> Unit = {}
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)) {
        Row(Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable { onClick(instrumentItem) }
            .border(1.dp, MaterialTheme.colors.onSurface.copy(alpha = 0.1f), MaterialTheme.shapes.small)
            .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(
                    instrumentItem.symbol,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    instrumentItem.name,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                    maxLines = 1,
                    overflow = Ellipsis
                )
            }
            Text(
                instrumentItem.lastSalePrice,
                Modifier
                    .padding(start = 16.dp)
                    .defaultMinSize(minWidth = 60.dp),
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End
            )
            Text(
                instrumentItem.percentageChange,
                Modifier
                    .padding(start = 16.dp)
                    .defaultMinSize(minWidth = 60.dp),
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                color = when (instrumentItem.deltaIndicator) {
                    DeltaIndicator.Up -> Color(0xFF53AD8A) // TODO: separate
                    DeltaIndicator.Down -> Color(0xFFDE536D) // TODO: separate
                    DeltaIndicator.NoChange -> MaterialTheme.colors.onSurface
                }
            )
        }
    }
}

private class FakeInstrumentItems2 : PreviewParameterProvider<ListItem> {
    override val values: Sequence<ListItem>
        get() = sequenceOf(
            ListItem.InstrumentItem(
                symbol = "IBM",
                name = "International Business Machines Corp",
                lastSalePrice = "$15.8",
                percentageChange = "-0.5%",
                deltaIndicator = DeltaIndicator.Down
            ),
            ListItem.InstrumentItem(
                symbol = "IBM",
                name = "International Business Machines Corp",
                lastSalePrice = "$15.8",
                percentageChange = "0.5%",
                deltaIndicator = DeltaIndicator.Up
            ),
            ListItem.InstrumentItem(
                symbol = "IBM",
                name = "International Business Machines Corp",
                lastSalePrice = "$15.8",
                percentageChange = "0.0%",
                deltaIndicator = DeltaIndicator.NoChange
            )
        )
}