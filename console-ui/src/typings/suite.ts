export interface SuiteMarket {
  id: number | null;
  suiteCode: string;
  suiteName: string;
  suiteImage: string;
  suiteDesc: string;
}

export interface SuiteMarketInfo {
  id: number | null;
  suiteCode: string;
  suiteName: string;
  suiteImage: string;
  suiteDesc: string;
  priceStatus: number;
  suitePrice: number;
  suiteHelpDocJson: string;
  installStatus: boolean;
  apiList: any[];
}