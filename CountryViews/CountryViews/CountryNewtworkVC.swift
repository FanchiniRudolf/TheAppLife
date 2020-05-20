//
//  CountryNewtworkVC.swift
//  CountryViews
//
//  Created by user167967 on 5/20/20.
//  Copyright © 2020 ITESM. All rights reserved.
//

import UIKit

class CountryNewtworkVC: UIViewController {
    
    @IBOutlet weak var table: UITableView!
    private var arrCountries = [Country]()

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        downloadCountries()
    }
    
    func downloadCountries(){
        let address = "https://restcountries.eu/rest/v2/all"
        let url = URL(string: address)!
        
        var request = URLRequest(url:url)
        
        request.httpMethod = "GET"
        
        request.addValue("application/json", forHTTPHeaderField: "Content-type")
        
        let task = URLSession.shared.dataTask(with: request){
            (data, response, error) in
            if error == nil {
                let anwser = response as! HTTPURLResponse
                if anwser.statusCode == 200{
                    if  let binData = data {
                        let json = try! JSONSerialization.jsonObject(with: binData, options: .mutableContainers) as! [[String:AnyObject]]
                        
                        self.constructCountryArr(daJSON: json)
                        print(self.arrCountries.count)
                        print(self.arrCountries[0])
                        
                        //New data
                        DispatchQueue.main.async {
                            self.table.reloadData()
                        }
                        
                    }
                }
            }else{
                print("error")
            }
        }
        task.resume()
    }
    
    func constructCountryArr(daJSON json: [[String:AnyObject]]){
        for dCountry in json{
            let countryName = dCountry["name"] as! String
            let countryCapital = dCountry["capital"] as! String
            let countryPopulation = dCountry["population"] as! Int
            
            let country = Country(countryName, countryCapital, countryPopulation)
            arrCountries.append(country)
        }
    }
    
    
    
    
    
}


extension CountryNewtworkVC: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return arrCountries.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "netCountryCell", for: indexPath)
        
        let index = indexPath.row
        let country = arrCountries[index]
        cell.textLabel?.text = country.name
        let subtitule = "\(country.capital), \(country.population)"
        cell.detailTextLabel?.text = subtitule
        
        return cell
        
    }
    
   
    
}
