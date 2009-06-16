package shm.common
{
	import mx.formatters.DateFormatter;
	import mx.formatters.Formatter;
	
	public class DateColumnFormatter extends ColumnFormatter
	{

		protected override function createFormatter():Formatter
		{
			var df:DateFormatter = new DateFormatter();
			df.formatString = "YYYY/MM/DD(EEE)";
			return df;
		}
	}
}