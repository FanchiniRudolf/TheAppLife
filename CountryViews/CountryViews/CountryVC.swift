//
//  CountryVC.swift
//  CountryViews
//
//  Created by user167967 on 5/13/20.
//  Copyright © 2020 ITESM. All rights reserved.
//

import UIKit
import WebKit

class Country_VC: UIViewController, WKNavigationDelegate {
    
    
    @IBOutlet weak var lblCountry: UILabel!
    
    @IBOutlet weak var actWait: UIActivityIndicatorView!
    
    @IBOutlet weak var webCountry: WKWebView!
    
    var countryName: String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        if countryName != ""{
            lblCountry.text = countryName
            loadPage(countryName)
        }
    }
    
    func loadPage(_ countryname: String){
        let address = "https://es.wikipedia.org/wiki/\(countryName)"
        let formatedAddress = address.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        let Url = URL(string: formatedAddress)!
        let webRequest = URLRequest(url: Url)
        webCountry.load(webRequest)
        
        webCountry.navigationDelegate = self
    }
    
    func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
        actWait.stopAnimating()
    }

}
