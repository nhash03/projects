import {BsFacebook} from 'react-icons/bs';
import {CiLinkedin, CiTwitter, CiInstagram} from 'react-icons/ci';
export const nav = [
  {
    text: "home",
    path: "/",
  },
  {
    text: "about",
    path: "/about",
  },
  {
    text: "services",
    path: "/services",
  },
  {
    text: "blog",
    path: "/blog",
  },
  {
    text: "pricing",
    path: "/pricing",
  },
  {
    text: "contact",
    path: "/contact",
  },
]

export const list = [
  {
    id: 1,
    cover: "/shelter-finder/public/images/list/p-1.png",
    name: "Blah",
    location: "210 Zirak Road, Canada",
    occupants: "4",
    type: "Apartment",
  },
  {
    id: 2,
    cover: "./images/team-1.png",
    name: "Blah 2",
    location: "5698 Zirak Road, NewYork",
    occupants: "4",
    type: "Condos",
  },
  {
    id: 3,
    cover: "/shelter-finder/public/images/list/p-3.png",
    name: "blah 3",
    location: "5624 Mooker Market, USA",
    occupants: "3",
    type: "Offices",
  },
  {
    id: 4,
    cover: "/shelter-finder/public/images/list/p-4.png",
    name: "blah 4",
    location: "5621 Liverpool, London",
    occupants: "5",
    type: "Homes & Villas",
  },
  {
    id: 5,
    cover: "/shelter-finder/public/images/list/p-5.png",
    name: "Blah 5",
    location: "210 Montreal Road, Canada",
    occupants: "5",
    type: "Commercial",
  },
  {
    id: 6,
    cover: "/shelter-finder/public/images/list/p-6.png",
    name: "blah 6",
    location: "210 Zirak Road, Canada",
    occupants: "1",
    type: "Apartment",
  },
]
export const location = [
  {
    id: 1,
    name: "Vancouver, British Columbia",
    hosts: "12 Hosts",
    cover: "./images/city-1.png",
  },
  {
    id: 2,
    name: "Istanbul, Turkey",
    hosts: "12 Hosts",
    cover: "./images/city-2.png",
  },
  {
    id: 3,
    name: "Liverpool, London",
    hosts: "12 Hosts",
    cover: "./images/city-3.png",
  },
  {
    id: 4,
    name: "NewYork, United States",
    hosts: "12 Hosts",
    cover: "./images/city-4.png",
  },
  {
    id: 5,
    name: "Montreal, Canada",
    hosts: "12 Hosts",
    cover: "./images/city-5.png",
  },
  {
    id: 6,
    name: "California, USA",
    hosts: "12 Hosts",
    cover: "./images/city-6.png",
  },
]
export const team = [
  {
    list: "10",
    cover: "./images/team-1.png",
    address: "Vancouver, Canada",
    name: "Setareh",
    icon: [<BsFacebook/>, <CiLinkedin />, <CiTwitter />, <CiInstagram />],
  },
  {
    list: "11",
    cover: "./images/team-2.png",
    address: "Montreal, Canada",
    name: "Negin",
    icon: [<BsFacebook/>,  <CiLinkedin />, <CiTwitter />, <CiInstagram />],
  },
  {
    list: "9",
    cover: "./images/team-3.png",
    address: "New York, USA",
    name: "Jooje ordack e zesht",
    icon: [<BsFacebook/>,  <CiLinkedin />,<CiTwitter />, <CiInstagram />],
  },
]

export const footer = [
  {
    title: "DISCOVER",
    text: [{ list: ["Home" , "Pages"]}, { list: ["AboutUs" , "Pages"]}, { list: ["Donation", "Pages"] }, {list: ["Contact Us", "Pages"]}],
  },
  {
    title: "JOIN US",
    text: [{ list: ["HostProfile", "Pages"]}, { list: ["GuestProfile", "Pages"] }, ],
  },
  {
    title: "FIND",
    text: [{ list: ["DiscoverByCity", "Pages"] }, { list: ["Filter", "Pages"] }, { list: ["AllOptions", "Pages"] },],
  },
]
