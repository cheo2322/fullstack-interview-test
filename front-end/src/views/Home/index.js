import {
  AstroMonaImage,
  BackgroundContainer,
  BackgroundImage,
  WelcomeText,
} from "./Home.styles";

const Home = () => {
  const hero_glow =
    "https://github.githubassets.com/images/modules/site/home/hero-glow.svg";

  const astro_mona =
    "https://github.githubassets.com/images/modules/site/home/astro-mona.svg";

  return (
    <>
      <WelcomeText>Where the world builds software</WelcomeText>
      <BackgroundContainer>
        <BackgroundImage src={hero_glow} alt="background" />
      </BackgroundContainer>
      <AstroMonaImage src={astro_mona} alt="astro mona" />
    </>
  );
};

export default Home;
