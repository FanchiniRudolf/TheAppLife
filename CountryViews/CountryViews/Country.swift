//
//  Country.swift
//  CountryViews
//
//  Created by user167967 on 5/20/20.
//  Copyright © 2020 ITESM. All rights reserved.
//

import Foundation

class Country
{
    var name:String
    var capital: String
    var population: Int
    
    init(_ name:String, _ capital : String, _ population: Int = 0){
        self.name = name
        self.capital = capital
        self.population = population
    }
    
}
