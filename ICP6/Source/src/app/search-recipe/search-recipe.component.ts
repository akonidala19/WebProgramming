import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe', { static: true }) recipes: ElementRef;
  @ViewChild('place', { static: true }) places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList = [];
  recipeList = [];
  formattedaddress = [];
  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) {
      this._http.get('https://api.edamam.com/search?q=' + this.recipeValue +
        '&app_id=036cd118&app_key=287414f6501087839c2261422a63bc1b').subscribe((recipes: any) => {
        this.recipeList = Object.keys(recipes.hits).map(function (rec) {
          const recipe = recipes.hits[rec].recipe;
          console.log(recipe.digest[0].schemaOrgTag);

          return {name: recipe.label,content:recipe.digest[0].schemaOrgTag, icon: recipe.image, add:recipe.address, url: recipe.url}
        });
      });


    }

    if (this.placeValue != null && this.placeValue !== '') {
      this._http.get('https://api.foursquare.com/v2/venues/search?client_id=NPGDHMQ303HYGLDQGT0JDTN0OLXGWHULRUZNEHBZDCNMQ0CZ' +
        '&client_secret=TAPEGXUJWCDDC5MPPPH5QETWGMCBUFFZ53JDUJHUXKUHOEVH&v=20200625&near=' + this.placeValue +'&query='+this.recipeValue).
      subscribe((restaurants:any) => {
        console.log("restaurants")
        this.venueList = Object.keys(restaurants.response.venues).map(function (input) {
          const restaurant = restaurants.response.venues[input];

          return {name: restaurant.name,currentLat:restaurant.location.labeledLatLngs[0].lng,currentLong:restaurant.location.labeledLatLngs[0].lat, formattedAddress: restaurant.location.formattedAddress};

        })
      }, error => {});
    }
  }
}
