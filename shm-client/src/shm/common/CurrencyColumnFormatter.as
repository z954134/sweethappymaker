package shm.common
{
	import mx.formatters.CurrencyFormatter;
	import mx.formatters.Formatter;
	
	public class CurrencyColumnFormatter extends ColumnFormatter
	{
		protected override function createFormatter():Formatter
		{
			var cf:CurrencyFormatter = new CurrencyFormatter();
			cf.alignSymbol= "right";
			cf.currencySymbol = "円";
			return cf;
		}
	}
}