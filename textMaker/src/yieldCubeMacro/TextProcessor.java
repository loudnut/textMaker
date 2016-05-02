package yieldCubeMacro;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class TextProcessor {

	// private List<JstrField> sigmaItems;
	// private int sigmaItemCount;
	/*
	 * public strProcesser(String strProcessStepName_15, String
	 * strProcessStepName_16, String strMeaStepName_15, String
	 * strMeaStepName_16, List<JstrField> sigmaItems, int sigmaItemCount) {
	 * this.strProcessStepName_15 = strProcessStepName_15;
	 * this.strProcessStepName_16 = strProcessStepName_16;
	 * this.strMeaStepName_15 = strMeaStepName_15; this.strMeaStepName_16 =
	 * strMeaStepName_16; this.sigmaItems = sigmaItems; this.sigmaItemCount =
	 * sigmaItemCount; }
	 */

	private String strProcessStepName_15;
	private String strProcessStepName_16;
	private String strMeaStepName_15;
	private String strMeaStepName_16;

	private String strFilterRecipe;

	private String strThkMean_15;
	private String strThkMean_16;
	private String strThkMeanRefLine;
	private String strThkRange_15;
	private String strThkRange_16;
	private String strThkRangeRefLine;
	private String strTitle;

	private String thkMeanAxisMax;
	private String thkMeanAxisMin;
	private String thkRangeAxisMax;
	private String thkRangeAxisMin = "0";
	
	private boolean chckbxFilterFOnly;

	
	private String textProcessStepNameProd;
	private String textMeaStepNameProd;
	private int sigmaItemCount;
	private List<JTextField> sigmaItem;
	private List<JComboBox> sigmaItemComboBox;
	private List<JTextField> sigmaItemOther;
	
	private String dummyString;

	public TextProcessor(String dummyString) {
		this.dummyString = dummyString;
	}
	
	public TextProcessor(String textProcessStepNameProd, String textMeaStepNameProd, int sigmaItemCount,
			List<JTextField> sigmaItem, List<JComboBox> sigmaItemComboBox, List<JTextField> sigmaItemOther) {
		super();
		this.textProcessStepNameProd = textProcessStepNameProd;
		this.textMeaStepNameProd = textMeaStepNameProd;
		this.sigmaItemCount = sigmaItemCount;
		this.sigmaItem = sigmaItem;
		this.sigmaItemComboBox = sigmaItemComboBox;
		this.sigmaItemOther = sigmaItemOther;
	}

	public TextProcessor(String strProcessStepName_15, String strProcessStepName_16, String strMeaStepName_15,
			String strMeaStepName_16, String strFilterRecipe, String strThkMean_15, String strThkMean_16,
			String strThkMeanRefLine, String strThkRange_15, String strThkRange_16, String strThkRangeRefLine,
			String strTitle, boolean chckbxFilterFOnly) {
		this.strProcessStepName_15 = strProcessStepName_15;
		this.strProcessStepName_16 = strProcessStepName_16;
		this.strMeaStepName_15 = strMeaStepName_15;
		this.strMeaStepName_16 = strMeaStepName_16;
		this.strFilterRecipe = strFilterRecipe;
		this.strThkMean_15 = strThkMean_15;
		this.strThkMean_16 = strThkMean_16;
		this.strThkMeanRefLine = strThkMeanRefLine;
		this.strThkRange_15 = strThkRange_15;
		this.strThkRange_16 = strThkRange_16;
		this.strThkRangeRefLine = strThkRangeRefLine;
		this.strTitle = strTitle;
		this.chckbxFilterFOnly = chckbxFilterFOnly;

		String[] thkMeanRefLineString = strThkMeanRefLine.split(";");
		Float[] thkMeanRefLineFloat = new Float[thkMeanRefLineString.length];
		Float max = (float) 0;
		Float min = (float) 999999;
		for (int i = 0; i < thkMeanRefLineString.length; i++) {
			thkMeanRefLineFloat[i] = Float.parseFloat(thkMeanRefLineString[i]);
			if (i == 0) {
				max = thkMeanRefLineFloat[i];
				min = thkMeanRefLineFloat[i];
			} else {
				if (thkMeanRefLineFloat[i] > max) {
					max = thkMeanRefLineFloat[i];
				}
				if (thkMeanRefLineFloat[i] < min) {
					min = thkMeanRefLineFloat[i];
				}
			}
		}
		thkMeanAxisMax = ((Float) (max + ((max - min) / 4))).toString();
		thkMeanAxisMin = ((Float) (min - ((max - min) / 4))).toString();

		String[] thkRangeRefLineString = strThkRangeRefLine.split(";");
		Float[] thkRangeRefLineFloat = new Float[thkRangeRefLineString.length];
		max = (float) 0;
		min = (float) 0;
		for (int i = 0; i < thkRangeRefLineString.length; i++) {
			thkRangeRefLineFloat[i] = Float.parseFloat(thkRangeRefLineString[i]);
			if (i == 0) {
				max = thkRangeRefLineFloat[i];
			} else {
				if (thkRangeRefLineFloat[i] > max) {
					max = thkRangeRefLineFloat[i];
				}
			}
		}
		thkRangeAxisMax = ((Float) (max + ((max - min) / 4))).toString();
		thkRangeAxisMin = ((Float) (min - ((max - min) / 4))).toString();
	}

	public String makeMacro() {
		String macro = new String();
		if (chckbxFilterFOnly){
			macro = "//" + strTitle + " - Qual THK\nGlobalQuery {\n\tKEYLIST=(\n\t\tMfgFacilityId=\n\t\t\t\"FAB 15\",\n\t\t\t\"FAB 16\",\n\t\tType=LOT,\n\t\tSource=\n\t\t\t\"Sigma\",\n\t\tDayCount=\n\t\t\t\"14\",\n\t\tMfgProcessStep=\n\t\t\t\"" + strProcessStepName_16 + "\",\n\t\t\t\"" + strProcessStepName_16 + "\",\n\t\t\t\"" + strProcessStepName_15 + "\",\n\t),\n\tSITES=\n\t\tTaichung,\n\t\tHiroshima,\n\tVersion=\"3008.00\",\n\tITEMS=(\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"EquipmentId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"MfgFacilityId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"ProcessEndDateTime\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"EquipmentId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"MfgFacilityId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"ProcessEndDateTime\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"GeRM RECIPE - RUN_ATTR\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"GeRM RECIPE - RUN_ATTR\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkMean_16 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkRange_16 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkMean_15 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkRange_15 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkMean_16 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkRange_16 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkMean_15 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkRange_15 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_15 + "\",\n\t\t),\n\t),\n\tOPTIONS=(\n\t\tIncludeAllKeyListItems=False,\n\t\tLocation=\n\t\t\t\"C:\\MTApps\\IS_Frontend\\Yield3\\CSVFiles\",\n\t\tInlineParamHeaderType=\n\t\t\t\"REG_LONG\",\n\t\tPullAllWaferRuns=False,\n\t\tShowRework=False,\n\t\tLoadintoY3grid=True,\n\t\tCollateByItem=False,\n\t\tResetItemsGrid=False,\n\t\tFileName=\n\t\t\t\"Request.csv\",\n\t\tPrependPartTypetoLot#=False,\n\t\tKeepOpen/ShortText=False,\n\t\tOverwrite=False,\n\t\tRemoveStaticColumns=False,\n\t\tWithoutDesignsForHeaders=False,\n\t\tCollationDataSource=\n\t\t\t\"Probe ProcessRun\",\n\t\tStaticColumnsFilledPercentage=\n\t\t\t\"100\",\n\t\tResetLotList=False,\n\t\tProbeHeaderType=\n\t\t\t\"SHORT\",\n\t\tExpandLotlistGenealogy=False,\n\t\tRemoveSparseColumns=False,\n\t\tExcludeDeletedStepsFromLotList=False,\n\t\tFinalParamHeaderType=\n\t\t\t\"REG_LONG\",\n\t\tCopytocsvfile=False,\n\t\tCollateByStartLot=False,\n\t\tPullSplits=True,\n\t\tResetGUI=False,\n\t\tSparseColumnsEmptyPercentage=\n\t\t\t\"100\",\n\t\tUseProcessSpec=False,\n\t),\n};\n\nSaveFile {\n\tAppend=Suffix,\n\tFormat=\"yyyyMMddHHmmss\",\n\tFilename=\"C:\\MTApps\\IS_Frontend\\Yield3\\YDS Files\\" + strTitle + " - Qual THK_.yds\",\n};\n\nSortColumns {\n\tParameters=\n\t\t\"" + strProcessStepName_16 + "::RunData::ProcessEndDateTime , ASC\",\n\t\t\"" + strProcessStepName_15 + "::RunData::ProcessEndDateTime , ASC\",\n};\n\nFilterDeleteRule {\n\tRuleID=\n\t\t0,\n\t\t1,\n};\n\nFilterAddRule {\n\tBetween=OR,\n\tRuleName=\"[" + strProcessStepName_16 + "::RunData::GeRM RECIPE - RUN_ATTR] Matching pattern : (^VCDM$)\",\n\tEnable=True,\n\tWithin=AND,\n\tCondition=(\n\t\tCaseSensitive=False,\n\t\tExactMatch=False,\n\t\tType=MatchPattern,\n\t\tParameter=\"" + strProcessStepName_16 + "::RunData::GeRM RECIPE - RUN_ATTR\",\n\t\tCondName=\"[" + strProcessStepName_16 + "::RunData::GeRM RECIPE - RUN_ATTR] Matching pattern : (^VCDM$)\",\n\t\tRegularExpr=True,\n\t\tPattern=\"^VCDM$|^$\",\n\t\tSource=UI,\n\t),\n\tRuleID=0,\n\tInverse=True,\n};\n\nFilterAddRule {\n\tBetween=OR,\n\tRuleName=\"[" + strProcessStepName_15 + "::RunData::GeRM RECIPE - RUN_ATTR] Matching pattern : (" + strFilterRecipe + ")\",\n\tEnable=True,\n\tWithin=AND,\n\tCondition=(\n\t\tCaseSensitive=False,\n\t\tExactMatch=False,\n\t\tType=MatchPattern,\n\t\tParameter=\"" + strProcessStepName_15 + "::RunData::GeRM RECIPE - RUN_ATTR\",\n\t\tCondName=\"[" + strProcessStepName_15 + "::RunData::GeRM RECIPE - RUN_ATTR] Matching pattern : (" + strFilterRecipe + ")\",\n\t\tRegularExpr=True,\n\t\tPattern=\"^" + strFilterRecipe + "$|^$\",\n\t\tSource=UI,\n\t),\n\tRuleID=1,\n\tInverse=True,\n};\n\nFilterExecute;\n\nAddColumn {\n\tColumnName=\"MfgFacility\",\n\tFormula=\"CONCATENATE(X4,X7)\",\n\tResultNullWhenDataNull=False,\n\tParameters=(\n\t\tX4=\"" + strProcessStepName_16 + "::RunData::MfgFacilityId\",\n\t\tX7=\"" + strProcessStepName_15 + "::RunData::MfgFacilityId\",\n\t),\n\tBlankColumn=False,\n\tDataType=String,\n};\n\nAddColumn {\n\tColumnName=\"Time\",\n\tFormula=\"CONCATENATE(X5,X8)\",\n\tResultNullWhenDataNull=False,\n\tParameters=(\n\t\tX8=\"" + strProcessStepName_15 + "::RunData::ProcessEndDateTime\",\n\t\tX5=\"" + strProcessStepName_16 + "::RunData::ProcessEndDateTime\",\n\t),\n\tBlankColumn=False,\n\tDataType=String,\n};\n\nChangeDataType {\n\tNewDataType=DateTime,\n\tParameters=\n\t\t\"Time\",\n};\n\nAddColumn {\n\tColumnName=\"" + strTitle + " - Qual THK Mean" + "\",\n\tFormula=\"X11+X15\",\n\tResultNullWhenDataNull=False,\n\tParameters=(\n\t\tX15=\"" + strMeaStepName_15 + "::MeasurementData::Product::METRO::" + strThkMean_15 + "\",\n\t\tX11=\"" + strMeaStepName_16 + "::MeasurementData::Product::Raw Data::" + strThkMean_16 + "\",\n\t),\n\tBlankColumn=False,\n\tDataType=Float,\n};\n\nAddColumn {\n\tColumnName=\"" + strTitle + " - Qual THK Range" + "\",\n\tFormula=\"X12+X16\",\n\tResultNullWhenDataNull=False,\n\tParameters=(\n\t\tX16=\"" + strMeaStepName_15 + "::MeasurementData::Product::METRO::" + strThkRange_15 + "\",\n\t\tX12=\"" + strMeaStepName_16 + "::MeasurementData::Product::Raw Data::" + strThkRange_16 + "\",\n\t),\n\tBlankColumn=False,\n\tDataType=Float,\n};\n\nLineChart {\n\tOption=(\n\t\tStdYGallery=False,\n\t\tMovingAvgWinSize=10,\n\t\tShowLegend=True,\n\t\tShowTrendOnly=False,\n\t\tEWMASampleSize=10,\n\t\tShowOverallTrend=False,\n\t\tEWMAWeight=0.2,\n\t\tExcludeMissingValues=True,\n\t\tMedianFactor=3,\n\t\tRotateXAxis=True,\n\t\tLoadSigma=False,\n\t\tKernelBandwidthType=2,\n\t\tKernelBandwidthValue=1,\n\t\tTrendType=None,\n\t\tYParameterGalleryChecked=False,\n\t),\n\tXParameters=\n\t\t\"Time\",\n\tYParameters=\n\t\t\"" + strTitle + " - Qual THK Mean" + "\",\n\tSubGroup=(\n\t\tPartition=\n\t\t\t\"MfgFacility\",\n\t),\n\tGallery=(\n\t\tGalleryLayoutRows=2,\n\t\tGalleryLayoutColumns=2,\n\t),\n};\n\nRenameChart {\n\tFrom=\"Line\",\n\tTo=\"" + strTitle + " - Qual THK Mean" + "\",\n};\n\nChartAddReference {\n\tTitle=\"THK Me vs Time\",\n\tWindowName=\"" + strTitle + " - Qual THK Mean" + "\",\n\tAxis=AxisY,\n\tLabel=\"" + strThkMeanRefLine + "\",\n};\n\nSetChartAxis {\n\tTitle=\"THK Me vs Time\",\n\tWindowName=\"" + strTitle + " - Qual THK Mean" + "\",\n\tMin=" + thkMeanAxisMin + ",\n\tMax=" + thkMeanAxisMax + ",\n\tDecimals=0,\n\tAxis=AxisY,\n};\n\nCopyToPowerPoint;\n\n\nLineChart {\n\tOption=(\n\t\tStdYGallery=False,\n\t\tMovingAvgWinSize=10,\n\t\tShowLegend=True,\n\t\tShowTrendOnly=False,\n\t\tEWMASampleSize=10,\n\t\tShowOverallTrend=False,\n\t\tEWMAWeight=0.2,\n\t\tExcludeMissingValues=True,\n\t\tMedianFactor=3,\n\t\tRotateXAxis=True,\n\t\tLoadSigma=False,\n\t\tKernelBandwidthType=2,\n\t\tKernelBandwidthValue=1,\n\t\tTrendType=None,\n\t\tYParameterGalleryChecked=False,\n\t),\n\tXParameters=\n\t\t\"Time\",\n\tYParameters=\n\t\t\"" + strTitle + " - Qual THK Range" + "\",\n\tSubGroup=(\n\t\tPartition=\n\t\t\t\"MfgFacility\",\n\t),\n\tGallery=(\n\t\tGalleryLayoutRows=2,\n\t\tGalleryLayoutColumns=2,\n\t),\n};\n\nRenameChart {\n\tFrom=\"Line\",\n\tTo=\"" + strTitle + " - Qual THK Range" + "\",\n};\n\nChartAddReference {\n\tTitle=\"" + strTitle + " - Qual THK Range" + " vs Time\",\n\tWindowName=\"" + strTitle + " - Qual THK Range" + "\",\n\tAxis=AxisY,\n\tLabel=\"" + strThkRangeRefLine + "\",\n};\n\nSetChartAxis {\n\tTitle=\"" + strTitle + " - Qual THK Range" + " vs Time\",\n\tWindowName=\"" + strTitle + " - Qual THK Range" + "\",\n\tMin=" + thkRangeAxisMin + ",\n\tMax=" + thkRangeAxisMax + ",\n\tAxis=AxisY,\n};\n\nCopyToPowerPoint;\n\n\n\n\n\nSavePowerPointPresentationAs {\n\tAppend=Suffix,\n\tFormat=\"yyyyMMddHHmmss\",\n\tFilename=\"\\\\10.20.33.132\\PEE2 DIFF Storage\\temp Y3 macro test\\" + strTitle + "_.pptx\",\n//\tFilename=\"\\\\10.20.33.132\\PEE2 DIFF Storage\\SPC Daily review\\SPC Daily Qual THK_.pptx\"\n};";
		}
		else {
			macro = "//" + strTitle + " - Qual THK\nGlobalQuery {\n\tKEYLIST=(\n\t\tMfgFacilityId=\n\t\t\t\"FAB 15\",\n\t\t\t\"FAB 16\",\n\t\tType=LOT,\n\t\tSource=\n\t\t\t\"Sigma\",\n\t\tDayCount=\n\t\t\t\"14\",\n\t\tMfgProcessStep=\n\t\t\t\"" + strProcessStepName_16 + "\",\n\t\t\t\"" + strProcessStepName_16 + "\",\n\t\t\t\"" + strProcessStepName_15 + "\",\n\t),\n\tSITES=\n\t\tTaichung,\n\t\tHiroshima,\n\tVersion=\"3008.00\",\n\tITEMS=(\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"EquipmentId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"MfgFacilityId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"ProcessEndDateTime\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"EquipmentId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"MfgFacilityId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"ProcessEndDateTime\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"GeRM RECIPE - RUN_ATTR\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"GeRM RECIPE - RUN_ATTR\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strProcessStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkMean_16 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkRange_16 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkMean_15 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkRange_15 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_16 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkMean_16 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkRange_16 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkMean_15 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_15 + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + strThkRange_15 + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + strMeaStepName_15 + "\",\n\t\t),\n\t),\n\tOPTIONS=(\n\t\tIncludeAllKeyListItems=False,\n\t\tLocation=\n\t\t\t\"C:\\MTApps\\IS_Frontend\\Yield3\\CSVFiles\",\n\t\tInlineParamHeaderType=\n\t\t\t\"REG_LONG\",\n\t\tPullAllWaferRuns=False,\n\t\tShowRework=False,\n\t\tLoadintoY3grid=True,\n\t\tCollateByItem=False,\n\t\tResetItemsGrid=False,\n\t\tFileName=\n\t\t\t\"Request.csv\",\n\t\tPrependPartTypetoLot#=False,\n\t\tKeepOpen/ShortText=False,\n\t\tOverwrite=False,\n\t\tRemoveStaticColumns=False,\n\t\tWithoutDesignsForHeaders=False,\n\t\tCollationDataSource=\n\t\t\t\"Probe ProcessRun\",\n\t\tStaticColumnsFilledPercentage=\n\t\t\t\"100\",\n\t\tResetLotList=False,\n\t\tProbeHeaderType=\n\t\t\t\"SHORT\",\n\t\tExpandLotlistGenealogy=False,\n\t\tRemoveSparseColumns=False,\n\t\tExcludeDeletedStepsFromLotList=False,\n\t\tFinalParamHeaderType=\n\t\t\t\"REG_LONG\",\n\t\tCopytocsvfile=False,\n\t\tCollateByStartLot=False,\n\t\tPullSplits=True,\n\t\tResetGUI=False,\n\t\tSparseColumnsEmptyPercentage=\n\t\t\t\"100\",\n\t\tUseProcessSpec=False,\n\t),\n};\n\nSaveFile {\n\tAppend=Suffix,\n\tFormat=\"yyyyMMddHHmmss\",\n\tFilename=\"C:\\MTApps\\IS_Frontend\\Yield3\\YDS Files\\" + strTitle + " - Qual THK_.yds\",\n};\n\nSortColumns {\n\tParameters=\n\t\t\"" + strProcessStepName_16 + "::RunData::ProcessEndDateTime , ASC\",\n\t\t\"" + strProcessStepName_15 + "::RunData::ProcessEndDateTime , ASC\",\n};\n\nFilterDeleteRule {\n\tRuleID=\n\t\t0,\n\t\t1,\n};\n\nFilterAddRule {\n\tBetween=OR,\n\tRuleName=\"[" + strProcessStepName_16 + "::RunData::GeRM RECIPE - RUN_ATTR] Matching pattern : (" + strFilterRecipe + ")\",\n\tEnable=True,\n\tWithin=AND,\n\tCondition=(\n\t\tCaseSensitive=False,\n\t\tExactMatch=False,\n\t\tType=MatchPattern,\n\t\tParameter=\"" + strProcessStepName_16 + "::RunData::GeRM RECIPE - RUN_ATTR\",\n\t\tCondName=\"[" + strProcessStepName_16 + "::RunData::GeRM RECIPE - RUN_ATTR] Matching pattern : (" + strFilterRecipe + ")\",\n\t\tRegularExpr=False,\n\t\tPattern=\"" + strFilterRecipe + "\",\n\t\tSource=UI,\n\t),\n\tRuleID=0,\n\tInverse=True,\n};\n\nFilterAddRule {\n\tBetween=AND,\n\tRuleName=\"[" + strProcessStepName_15 + "::RunData::GeRM RECIPE - RUN_ATTR] Matching pattern : (" + strFilterRecipe + ")\",\n\tEnable=True,\n\tWithin=AND,\n\tCondition=(\n\t\tCaseSensitive=False,\n\t\tExactMatch=False,\n\t\tType=MatchPattern,\n\t\tParameter=\"" + strProcessStepName_15 + "::RunData::GeRM RECIPE - RUN_ATTR\",\n\t\tCondName=\"[" + strProcessStepName_15 + "::RunData::GeRM RECIPE - RUN_ATTR] Matching pattern : (" + strFilterRecipe + ")\",\n\t\tRegularExpr=False,\n\t\tPattern=\"" + strFilterRecipe + "\",\n\t\tSource=UI,\n\t),\n\tRuleID=1,\n\tInverse=True,\n};\n\nFilterExecute;\n\n\n\n\nAddColumn {\n\tColumnName=\"MfgFacility\",\n\tFormula=\"CONCATENATE(X4,X7)\",\n\tResultNullWhenDataNull=False,\n\tParameters=(\n\t\tX4=\"" + strProcessStepName_16 + "::RunData::MfgFacilityId\",\n\t\tX7=\"" + strProcessStepName_15 + "::RunData::MfgFacilityId\",\n\t),\n\tBlankColumn=False,\n\tDataType=String,\n};\n\nAddColumn {\n\tColumnName=\"Time\",\n\tFormula=\"CONCATENATE(X5,X8)\",\n\tResultNullWhenDataNull=False,\n\tParameters=(\n\t\tX8=\"" + strProcessStepName_15 + "::RunData::ProcessEndDateTime\",\n\t\tX5=\"" + strProcessStepName_16 + "::RunData::ProcessEndDateTime\",\n\t),\n\tBlankColumn=False,\n\tDataType=String,\n};\n\nChangeDataType {\n\tNewDataType=DateTime,\n\tParameters=\n\t\t\"Time\",\n};\n\nAddColumn {\n\tColumnName=\"" + strTitle + " - Qual THK Mean" + "\",\n\tFormula=\"X11+X15\",\n\tResultNullWhenDataNull=False,\n\tParameters=(\n\t\tX15=\"" + strMeaStepName_15 + "::MeasurementData::Product::METRO::" + strThkMean_15 + "\",\n\t\tX11=\"" + strMeaStepName_16 + "::MeasurementData::Product::Raw Data::" + strThkMean_16 + "\",\n\t),\n\tBlankColumn=False,\n\tDataType=Float,\n};\n\nAddColumn {\n\tColumnName=\"" + strTitle + " - Qual THK Range" + "\",\n\tFormula=\"X12+X16\",\n\tResultNullWhenDataNull=False,\n\tParameters=(\n\t\tX16=\"" + strMeaStepName_15 + "::MeasurementData::Product::METRO::" + strThkRange_15 + "\",\n\t\tX12=\"" + strMeaStepName_16 + "::MeasurementData::Product::Raw Data::" + strThkRange_16 + "\",\n\t),\n\tBlankColumn=False,\n\tDataType=Float,\n};\n\nLineChart {\n\tOption=(\n\t\tStdYGallery=False,\n\t\tMovingAvgWinSize=10,\n\t\tShowLegend=True,\n\t\tShowTrendOnly=False,\n\t\tEWMASampleSize=10,\n\t\tShowOverallTrend=False,\n\t\tEWMAWeight=0.2,\n\t\tExcludeMissingValues=True,\n\t\tMedianFactor=3,\n\t\tRotateXAxis=True,\n\t\tLoadSigma=False,\n\t\tKernelBandwidthType=2,\n\t\tKernelBandwidthValue=1,\n\t\tTrendType=None,\n\t\tYParameterGalleryChecked=False,\n\t),\n\tXParameters=\n\t\t\"Time\",\n\tYParameters=\n\t\t\"" + strTitle + " - Qual THK Mean" + "\",\n\tSubGroup=(\n\t\tPartition=\n\t\t\t\"MfgFacility\",\n\t),\n\tGallery=(\n\t\tGalleryLayoutRows=2,\n\t\tGalleryLayoutColumns=2,\n\t),\n};\n\nRenameChart {\n\tFrom=\"Line\",\n\tTo=\"" + strTitle + " - Qual THK Mean" + "\",\n};\n\nChartAddReference {\n\tTitle=\"THK Me vs Time\",\n\tWindowName=\"" + strTitle + " - Qual THK Mean" + "\",\n\tAxis=AxisY,\n\tLabel=\"" + strThkMeanRefLine + "\",\n};\n\nSetChartAxis {\n\tTitle=\"THK Me vs Time\",\n\tWindowName=\"" + strTitle + " - Qual THK Mean" + "\",\n\tMin=" + thkMeanAxisMin + ",\n\tMax=" + thkMeanAxisMax + ",\n\tDecimals=0,\n\tAxis=AxisY,\n};\n\nCopyToPowerPoint;\n\n\nLineChart {\n\tOption=(\n\t\tStdYGallery=False,\n\t\tMovingAvgWinSize=10,\n\t\tShowLegend=True,\n\t\tShowTrendOnly=False,\n\t\tEWMASampleSize=10,\n\t\tShowOverallTrend=False,\n\t\tEWMAWeight=0.2,\n\t\tExcludeMissingValues=True,\n\t\tMedianFactor=3,\n\t\tRotateXAxis=True,\n\t\tLoadSigma=False,\n\t\tKernelBandwidthType=2,\n\t\tKernelBandwidthValue=1,\n\t\tTrendType=None,\n\t\tYParameterGalleryChecked=False,\n\t),\n\tXParameters=\n\t\t\"Time\",\n\tYParameters=\n\t\t\"" + strTitle + " - Qual THK Range" + "\",\n\tSubGroup=(\n\t\tPartition=\n\t\t\t\"MfgFacility\",\n\t),\n\tGallery=(\n\t\tGalleryLayoutRows=2,\n\t\tGalleryLayoutColumns=2,\n\t),\n};\n\nRenameChart {\n\tFrom=\"Line\",\n\tTo=\"" + strTitle + " - Qual THK Range" + "\",\n};\n\nChartAddReference {\n\tTitle=\"" + strTitle + " - Qual THK Range" + " vs Time\",\n\tWindowName=\"" + strTitle + " - Qual THK Range" + "\",\n\tAxis=AxisY,\n\tLabel=\"" + strThkRangeRefLine + "\",\n};\n\nSetChartAxis {\n\tTitle=\"" + strTitle + " - Qual THK Range" + " vs Time\",\n\tWindowName=\"" + strTitle + " - Qual THK Range" + "\",\n\tMin=" + thkRangeAxisMin + ",\n\tMax=" + thkRangeAxisMax + ",\n\tAxis=AxisY,\n};\n\nCopyToPowerPoint;\n\n\n\n\n\nSavePowerPointPresentationAs {\n\tAppend=Suffix,\n\tFormat=\"yyyyMMddHHmmss\",\n\tFilename=\"\\\\10.20.33.132\\PEE2 DIFF Storage\\temp Y3 macro test\\" + strTitle + "_.pptx\",\n//\tFilename=\"\\\\10.20.33.132\\PEE2 DIFF Storage\\SPC Daily review\\SPC Daily Qual THK_.pptx\"\n};";
		}
		return macro;
	}
	
	private String findAxisBounds(String inputLimits) {
		String[] inputLimitsString = inputLimits.split(";");
		Float[] inputLimitsFloat = new Float[inputLimitsString.length];
		Float max = (float) 0;
		Float min = (float) 999999;
		if (inputLimitsString.length >= 3){
			for (int i = 0; i < inputLimitsString.length; i++) {
				inputLimitsFloat[i] = Float.parseFloat(inputLimitsString[i]);
				if (i == 0) {
					max = inputLimitsFloat[i];
					min = inputLimitsFloat[i];
				} else {
					if (inputLimitsFloat[i] > max) 
						max = inputLimitsFloat[i];
					if (inputLimitsFloat[i] < min) 
						min = inputLimitsFloat[i];
				}
			}
			return ((Float) (min - ((max - min) / 4))).toString() + ";" + ((Float) (max + ((max - min) / 4))).toString();
		} else if (inputLimitsString.length == 0){
			return "0;10";
		} else {
			max = (float) 0;
			min = (float) 0;
			for (int i = 0; i < inputLimitsString.length; i++) {
				inputLimitsFloat[i] = Float.parseFloat(inputLimitsString[i]);
				if (i == 0) 
					max = inputLimitsFloat[i];
				 else 
					if (inputLimitsFloat[i] > max) 
						max = inputLimitsFloat[i];
			}
			return ((Float) (min - ((max - min) / 4))).toString() + ";" + ((Float) (max + ((max - min) / 4))).toString();
		}
	}
	
	public String makeMacroProd() {
		StringBuffer macro = new StringBuffer("");

		// Global Query
		macro.append("//" + textProcessStepNameProd + " - Prod\nGlobalQuery {\n\tKEYLIST=(\n\t\tDesignId=\n\t\t\t\"Z11C\",\n\t\tType=LOT,\n\t\tDayCount=\n\t\t\t\"14\",\n\t\tMfgFacilityId=\n\t\t\t\"FAB 15\",\n\t\t\t\"FAB 16\",\n\t\tMfgProcessStep=\n\t\t\t\"" + textProcessStepNameProd + "\",\n\t\tSource=\n\t\t\t\"Sigma\",\n\t),\n\tSITES=\n\t\tTaichung,\n\t\tHiroshima,\n\tVersion=\"3008.00\",\n\tITEMS=(\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"EquipmentId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + textProcessStepNameProd + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"MfgFacilityId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + textProcessStepNameProd + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"ProcessEndDateTime\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + textProcessStepNameProd + "\",\n\t\t),\n");
		for (int i = 1; i <= sigmaItemCount; i++){
			macro.append("\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + sigmaItem.get(i * 2 - 2).getText() + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + textMeaStepNameProd + "\",\n\t\t),\n");
		}
		macro.append("\t),\n\tOPTIONS=(\n\t\tIncludeAllKeyListItems=False,\n\t\tLocation=\n\t\t\t\"C:\\MTApps\\IS_Frontend\\Yield3\\CSVFiles\",\n\t\tInlineParamHeaderType=\n\t\t\t\"REG_LONG\",\n\t\tPullAllWaferRuns=False,\n\t\tShowRework=False,\n\t\tLoadintoY3grid=True,\n\t\tCollateByItem=False,\n\t\tResetItemsGrid=False,\n\t\tFileName=\n\t\t\t\"Request.csv\",\n\t\tPrependPartTypetoLot#=False,\n\t\tKeepOpen/ShortText=False,\n\t\tOverwrite=False,\n\t\tRemoveStaticColumns=False,\n\t\tWithoutDesignsForHeaders=False,\n\t\tCollationDataSource=\n\t\t\t\"Probe ProcessRun\",\n\t\tStaticColumnsFilledPercentage=\n\t\t\t\"100\",\n\t\tResetLotList=False,\n\t\tProbeHeaderType=\n\t\t\t\"SHORT\",\n\t\tExpandLotlistGenealogy=False,\n\t\tRemoveSparseColumns=False,\n\t\tExcludeDeletedStepsFromLotList=False,\n\t\tFinalParamHeaderType=\n\t\t\t\"REG_LONG\",\n\t\tCopytocsvfile=False,\n\t\tCollateByStartLot=False,\n\t\tPullSplits=True,\n\t\tResetGUI=False,\n\t\tSparseColumnsEmptyPercentage=\n\t\t\t\"100\",\n\t\tUseProcessSpec=False,\n\t),\n};\n\n");
		// Global Query
		
		// Data saving, sorting, renaming
		macro.append("SaveFile {\n\tAppend=Suffix,\n\tFormat=\"yyyyMMddHHmm\",\n\tFilename=\"C:\\MTApps\\IS_Frontend\\Yield3\\YDS Files\\" + textProcessStepNameProd + " - Prod_.yds\",\n};\nSortColumns {\n\tParameters=\n\t\t\"" + textProcessStepNameProd + "::RunData::MfgFacilityId , ASC\",\n\t\t\"" + textProcessStepNameProd + "::RunData::ProcessEndDateTime , ASC\",\n};\nRenameColumn {\n\tToColumn=\"Time\",\n\tFromColumn=\"" + textProcessStepNameProd + "::RunData::ProcessEndDateTime\",\n};\n");
		
		
		
		for (int i = 1; i <= sigmaItemCount; i++){
			StringBuffer sigmaItemBf = new StringBuffer(sigmaItem.get(i * 2 - 2).getText());
			sigmaItemBf.replace(sigmaItemBf.indexOf("("), sigmaItemBf.indexOf("(")+1, "\\(");
			sigmaItemBf.replace(sigmaItemBf.indexOf(")"), sigmaItemBf.indexOf(")")+1, "\\)");
			if (sigmaItemComboBox.get(i - 1).getSelectedItem().toString().equalsIgnoreCase("Other")){
				macro.append("RenameColumn {\n\tToColumn=\"" + textProcessStepNameProd + " - Prod " + sigmaItemOther.get(i - 1).getText() + "\",\n\tFromColumn=~*_SYS_GETDATA(match=\"" + sigmaItemBf.toString() + "\",quote=\"false\",data=\"COLUMNNAME\")*,\n};\n");
			} else {
				macro.append("RenameColumn {\n\tToColumn=\"" + textProcessStepNameProd + " - Prod " + sigmaItemComboBox.get(i - 1).getSelectedItem().toString() + "\",\n\tFromColumn=~*_SYS_GETDATA(match=\"" + sigmaItemBf.toString() + "\",quote=\"false\",data=\"COLUMNNAME\")*,\n};\n");
			}
		}
		// Data saving, sorting, renaming
		
		// Charting
		for (int i = 1; i <= sigmaItemCount; i++){
			if (sigmaItemComboBox.get(i - 1).getSelectedItem().toString().equalsIgnoreCase("Other")){
				macro.append("LineChart {\n\tOption=(\n\t\tStdYGallery=False,\n\t\tMovingAvgWinSize=10,\n\t\tShowLegend=True,\n\t\tShowTrendOnly=False,\n\t\tEWMASampleSize=10,\n\t\tShowOverallTrend=False,\n\t\tEWMAWeight=0.2,\n\t\tExcludeMissingValues=True,\n\t\tMedianFactor=3,\n\t\tRotateXAxis=True,\n\t\tLoadSigma=False,\n\t\tKernelBandwidthType=2,\n\t\tKernelBandwidthValue=1,\n\t\tTrendType=None,\n\t\tYParameterGalleryChecked=False,\n\t),\n\tXParameters=\n\t\t\"Time\",\n\tYParameters=\n\t\t\"" + textProcessStepNameProd + " - Prod " + sigmaItemOther.get(i - 1).getText() + "\",\n\tSubGroup=(\n\t\tPartition=\n\t\t\t\"" + textProcessStepNameProd + "::RunData::MfgFacilityId\",\n\t),\n\tGallery=(\n\t\tGalleryLayoutRows=2,\n\t\tGalleryLayoutColumns=2,\n\t),\n};\nRenameChart {\n\tFrom=\"Line\",\n\tTo=\"" + textProcessStepNameProd + " - Prod " + sigmaItemOther.get(i - 1).getText() + "\",\n};\nChartAddReference {\n\tTitle=\"" + textProcessStepNameProd + " - Prod " + sigmaItemOther.get(i - 1).getText() + " vs Time\",\n\tWindowName=\"" + textProcessStepNameProd + " - Prod " + sigmaItemOther.get(i - 1).getText() + "\",\n\tAxis=AxisY,\n\tLabel=\"" + sigmaItem.get(i * 2 - 1).getText().toString() + "\",\n};\nSetChartAxis {\n\tTitle=\"" + textProcessStepNameProd + " - Prod " + sigmaItemOther.get(i - 1).getText() + " vs Time\",\n\tWindowName=\"" + textProcessStepNameProd + " - Prod " + sigmaItemOther.get(i - 1).getText() + "\",\n\tMin=" + findAxisBounds(sigmaItem.get(i * 2 - 1).getText().toString()).substring(0, findAxisBounds(sigmaItem.get(i * 2 - 1).getText().toString()).indexOf(';')) + ",\n\tMax=" + findAxisBounds(sigmaItem.get(i * 2 - 1).getText().toString()).substring(findAxisBounds(sigmaItem.get(i * 2 - 1).getText().toString()).lastIndexOf(';') + 1) + ",\n\tAxis=AxisY,\n};\nCopyToPowerPoint;\n");
			} else {
				macro.append("LineChart {\n\tOption=(\n\t\tStdYGallery=False,\n\t\tMovingAvgWinSize=10,\n\t\tShowLegend=True,\n\t\tShowTrendOnly=False,\n\t\tEWMASampleSize=10,\n\t\tShowOverallTrend=False,\n\t\tEWMAWeight=0.2,\n\t\tExcludeMissingValues=True,\n\t\tMedianFactor=3,\n\t\tRotateXAxis=True,\n\t\tLoadSigma=False,\n\t\tKernelBandwidthType=2,\n\t\tKernelBandwidthValue=1,\n\t\tTrendType=None,\n\t\tYParameterGalleryChecked=False,\n\t),\n\tXParameters=\n\t\t\"Time\",\n\tYParameters=\n\t\t\"" + textProcessStepNameProd + " - Prod " + sigmaItemComboBox.get(i - 1).getSelectedItem().toString() + "\",\n\tSubGroup=(\n\t\tPartition=\n\t\t\t\"" + textProcessStepNameProd + "::RunData::MfgFacilityId\",\n\t),\n\tGallery=(\n\t\tGalleryLayoutRows=2,\n\t\tGalleryLayoutColumns=2,\n\t),\n};\nRenameChart {\n\tFrom=\"Line\",\n\tTo=\"" + textProcessStepNameProd + " - Prod " + sigmaItemComboBox.get(i - 1).getSelectedItem().toString() + "\",\n};\nChartAddReference {\n\tTitle=\"" + textProcessStepNameProd + " - Prod " + sigmaItemComboBox.get(i - 1).getSelectedItem().toString() + " vs Time\",\n\tWindowName=\"" + textProcessStepNameProd + " - Prod " + sigmaItemComboBox.get(i - 1).getSelectedItem().toString() + "\",\n\tAxis=AxisY,\n\tLabel=\"" + sigmaItem.get(i * 2 - 1).getText().toString() + "\",\n};\nSetChartAxis {\n\tTitle=\"" + textProcessStepNameProd + " - Prod " + sigmaItemComboBox.get(i - 1).getSelectedItem().toString() + " vs Time\",\n\tWindowName=\"" + textProcessStepNameProd + " - Prod " + sigmaItemComboBox.get(i - 1).getSelectedItem().toString() + "\",\n\tMin=" + findAxisBounds(sigmaItem.get(i * 2 - 1).getText().toString()).substring(0, findAxisBounds(sigmaItem.get(i * 2 - 1).getText().toString()).indexOf(';')) + ",\n\tMax=" + findAxisBounds(sigmaItem.get(i * 2 - 1).getText().toString()).substring(findAxisBounds(sigmaItem.get(i * 2 - 1).getText().toString()).lastIndexOf(';') + 1) + ",\n\tAxis=AxisY,\n};\nCopyToPowerPoint;\n");
			}
		}
		// Charting
		
		return macro.toString();
	}

	public String makeMacroProdGq() {
		StringBuffer macro = new StringBuffer("");
		macro.append("//" + textProcessStepNameProd + " - Prod\nGlobalQuery {\n\tKEYLIST=(\n\t\tDesignId=\n\t\t\t\"Z11C\",\n\t\tType=LOT,\n\t\tDayCount=\n\t\t\t\"14\",\n\t\tMfgFacilityId=\n\t\t\t\"FAB 15\",\n\t\t\t\"FAB 16\",\n\t\tMfgProcessStep=\n\t\t\t\"" + textProcessStepNameProd + "\",\n\t\tSource=\n\t\t\t\"Sigma\",\n\t),\n\tSITES=\n\t\tTaichung,\n\t\tHiroshima,\n\tVersion=\"3008.00\",\n\tITEMS=(\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"EquipmentId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + textProcessStepNameProd + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"MfgFacilityId\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + textProcessStepNameProd + "\",\n\t\t),\n\t\t_ITEM=(\n\t\t\tType=AttrItem,\n\t\t\tItem=\"ProcessEndDateTime\",\n\t\t\tLevel=Run,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + textProcessStepNameProd + "\",\n\t\t),\n");
		for (int i = 1; i <= sigmaItemCount; i++){
			macro.append("\t\t_ITEM=(\n\t\t\tType=TestItem,\n\t\t\tItem=\"" + sigmaItem.get(i * 2 - 2).getText() + "\",\n\t\t\tLevel=Measurement,\n\t\t\tDataSource=Sigma,\n\t\t\tStep=\"" + textMeaStepNameProd + "\",\n\t\t),\n");
		}
		macro.append("\t),\n\tOPTIONS=(\n\t\tIncludeAllKeyListItems=False,\n\t\tLocation=\n\t\t\t\"C:\\MTApps\\IS_Frontend\\Yield3\\CSVFiles\",\n\t\tInlineParamHeaderType=\n\t\t\t\"REG_LONG\",\n\t\tPullAllWaferRuns=False,\n\t\tShowRework=False,\n\t\tLoadintoY3grid=True,\n\t\tCollateByItem=False,\n\t\tResetItemsGrid=False,\n\t\tFileName=\n\t\t\t\"Request.csv\",\n\t\tPrependPartTypetoLot#=False,\n\t\tKeepOpen/ShortText=False,\n\t\tOverwrite=False,\n\t\tRemoveStaticColumns=False,\n\t\tWithoutDesignsForHeaders=False,\n\t\tCollationDataSource=\n\t\t\t\"Probe ProcessRun\",\n\t\tStaticColumnsFilledPercentage=\n\t\t\t\"100\",\n\t\tResetLotList=False,\n\t\tProbeHeaderType=\n\t\t\t\"SHORT\",\n\t\tExpandLotlistGenealogy=False,\n\t\tRemoveSparseColumns=False,\n\t\tExcludeDeletedStepsFromLotList=False,\n\t\tFinalParamHeaderType=\n\t\t\t\"REG_LONG\",\n\t\tCopytocsvfile=False,\n\t\tCollateByStartLot=False,\n\t\tPullSplits=True,\n\t\tResetGUI=False,\n\t\tSparseColumnsEmptyPercentage=\n\t\t\t\"100\",\n\t\tUseProcessSpec=False,\n\t),\n};\n\n");
		return macro.toString();
	}

	public String makeChartScale() {
		return "\tMin=" + findAxisBounds(dummyString).substring(0, findAxisBounds(dummyString).indexOf(';')) + ",\n\tMax=" + findAxisBounds(dummyString).substring(findAxisBounds(dummyString).lastIndexOf(';') + 1) + ",";
	}
}
