import { GoogleMap, LoadScript, Marker } from '@react-google-maps/api';
import "./nearby.css"

function Nearby ()  {
    return (
      <>
      <section className='Nearby'>
        <div className = "container">
          <div className='title'>
            <h1>Nearby Places to Stay</h1>
          </div>
            <LoadScript googleMapsApiKey="AIzaSyBHxfYdVB4thHzdJx_745JuI9acH7jDjKk">
            <GoogleMap
                 center={{ lat: 37.7749, lng: -122.4194 }}
                          zoom={8}
                mapContainerStyle={{ height: '70%', width: '80%', margin: "0px auto"}}
    >
              <Marker position={{ lat: 37.7749, lng: -122.4194 }} />
          </GoogleMap>
          </LoadScript>
        </div>
      </section>
      </>
    ) 
  } 
export default Nearby
